package com.recharge.servicedao;

import com.recharge.model.AckSlipAttchment;

public interface AckSlipAttachmentDao {

	public AckSlipAttchment getAckSlipAttchmentByApId(String ackno);

	public boolean insertAckSlipAttchment(AckSlipAttchment AckSlipAttchment);

}
