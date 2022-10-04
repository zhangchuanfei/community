package chuanfei.community.community.service;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import chuanfei.community.community.mapper.DealMapper;
import chuanfei.community.community.mapper.EntrustMapper;
import chuanfei.community.community.mapper.InstructionMapper;
import chuanfei.community.community.model.Deal;
import chuanfei.community.community.model.Entrust;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DealService {

    @Autowired
    private DealMapper dealMapper;

    @Autowired
    private EntrustMapper entrustMapper;

    @Autowired
    private InstructionMapper instructionMapper;

    private static ExecutorService ES = Executors.newFixedThreadPool(4);  //改为全局的，否则每进一次都建一个线程池

    public void create(Long  orderId){

        Entrust entrust = entrustMapper.findEntrust(orderId);
        Integer entrustId = entrust.getEntrustId();
        Float price = entrust.getSingleEntrustPrice();
        Integer number = entrust.getSingleEntrustNumber();
        Deal deal = new Deal();
        deal.setEntrustId(entrustId);
        deal.setOrderId(orderId);
        deal.setDealPrice(price);
        deal.setDealNumber(number / 4);

        //串行执行
       // serialCreate(deal, orderId, number);
        //线程池同步
        threadPoolSyn(deal, orderId, number);

    }

    public void serialCreate(Deal deal, Long  orderId, Integer number){
        Date date = new Date();
        for (int i = 0; i < 4; i++){
            deal.setDealTime(date.toString());
            dealMapper.create(deal);
            if (i != 3){
                instructionMapper.changeStatus("部成", orderId);
                Integer curNumber = instructionMapper.findNumber(orderId);
                instructionMapper.changeNumber(curNumber + number / 4, orderId);
            }else{
                instructionMapper.changeStatus("已成", orderId);
                Integer curNumber = instructionMapper.findNumber(orderId);
                instructionMapper.changeNumber(curNumber + number / 4, orderId);
            }

        }
    }
    public void threadPoolSyn(Deal deal, Long  orderId, Integer number){
        //ExecutorService ES = Executors.newFixedThreadPool(4);  //改为全局的，否则每进一次都建一个线程池
        Runnableimpl runnableimpl = new Runnableimpl(number, deal, orderId, number);
        for (int i = 0; i < 4; i++){
            ES.submit(runnableimpl);
        }
    }
    @Data
    class Runnableimpl implements Runnable {

        private int remain;
        private Object obj = new Object();
        private Deal deal;
        private Long orderId;
        private Integer number;


        public Runnableimpl(int remain, Deal deal, Long orderId, Integer number){
            this.remain = remain;
            this.setDeal(deal);
            this.setOrderId(orderId);
            this.setNumber(number);
        }

        @Override
        public void run() {

            synchronized (obj){

                deal.setDealTime(new Date().toString());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                dealMapper.create(deal);
                if (this.remain == this.number){
                    instructionMapper.changeStatus("部成", orderId);
                } else if (this.remain == this.number / 4){
                    instructionMapper.changeStatus("已成", orderId);
                }

                this.remain -= this.number / 4;
                Integer curNumber = instructionMapper.findNumber(orderId);
                instructionMapper.changeNumber(curNumber + number / 4, orderId);
                instructionMapper.changeModifyTime(new Date().toString(), orderId);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
