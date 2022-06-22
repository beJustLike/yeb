package com.xxxx.server.mapper;

import com.xxxx.server.pojo.Bursement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liyongkang
 * @since 2022-01-26
 */
public interface BursementMapper extends BaseMapper<Bursement> {

    Integer save(@Param("entity") Bursement bursement);

    Integer updateLater(@Param("id") Long bursementId,
                        @Param("money") double allDetailMoney,
                        @Param("invoices") int allInvoices);

    Integer auditUpdate(@Param("processId") Long processId, @Param("auditDes") String auditDes, Long userId);

    Integer auditUpdateSecond(@Param("processId") Long processId,@Param("auditDes") String auditDes,@Param("userId")Long userId);

    Integer auditUpdateThree(@Param("processId") Long processId,@Param("auditDes") String auditDes);

    Bursement getBurseRecord(Long processId);
}
