package chuanfei.community.community.service;

import chuanfei.community.community.mapper.EntrustMapper;
import chuanfei.community.community.model.Entrust;
import chuanfei.community.community.model.Instruction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntrustService {

    @Autowired
    private EntrustMapper entrustMapper;

    public List<Entrust> getEntrust(Long orderId){

        List<Entrust> entrust = entrustMapper.findByOrderId(orderId);
        return entrust;
    }
}
