package com.paic.ehis.base.service;


import com.paic.ehis.base.domain.HmpServContacts;

import java.util.List;

/**
 * 联系人Service接口
 *
 * @author sino
 * @date 2020-11-2
 */
public interface IHmpServContactsService {
    /**
     * 新增联系人
     * @param hmpServContacts
     * @return
     */
    int insertHmpServContacts(List<HmpServContacts> hmpServContacts);

    /**
     * 根据供应商编码查询联系人信息
     * @param servcomno
     * @return
     */
    List<HmpServContacts> selectHmpServContacts(String servcomno);

    /**
     * 根据供应商编码修改联系人信息
     * @param hmpServContactsList
     * @return
     */
    int updateHmpServContacts(List<HmpServContacts> hmpServContactsList);


    /**
     * 根据供应商编码查询联系人信息
     * @param servcomno
     * @return
     */
    int deleteHmpServContacts(String servcomno);
}
