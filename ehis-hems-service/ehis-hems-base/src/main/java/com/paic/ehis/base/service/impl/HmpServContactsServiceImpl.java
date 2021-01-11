package com.paic.ehis.base.service.impl;

import com.paic.ehis.base.domain.HmpServContacts;
import com.paic.ehis.base.mapper.HmpServContactsMapper;
import com.paic.ehis.base.service.IHmpServContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 联系人Service业务层处理
 *
 * @author sino
 * @date 2020-11-2
 */
@Service
public class HmpServContactsServiceImpl implements IHmpServContactsService {

    @Autowired
    private HmpServContactsMapper hmpServContactsMapper;

    @Override
    public int insertHmpServContacts(List<HmpServContacts> hmpServContacts) {

        return hmpServContactsMapper.insertHmpServContacts(hmpServContacts);
    }

    @Override
    public List<HmpServContacts> selectHmpServContacts(String servcomno) {
        return hmpServContactsMapper.selectHmpServContacts(servcomno);
    }

    @Override
    public int updateHmpServContacts(List<HmpServContacts> hmpServContactsList) {
        // 修改  为先删除 再增
        // 联系人至少为一条 因此是有servcomno的
        HmpServContacts hmpServContacts = hmpServContactsList.get(0);
        String servcomno = hmpServContacts.getServcomno();
        // 删除
        deleteHmpServContacts(servcomno);

        return hmpServContactsMapper.insertHmpServContacts(hmpServContactsList);
    }

    @Override
    public int deleteHmpServContacts(String servcomno) {
        return hmpServContactsMapper.deleteHmpServContacts(servcomno);
    }
}
