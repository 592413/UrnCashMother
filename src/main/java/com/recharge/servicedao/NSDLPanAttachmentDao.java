package com.recharge.servicedao;

import java.util.List;
import java.util.Map;

import com.recharge.model.NSDLPanAttachment;

public interface NSDLPanAttachmentDao {

	public boolean insertNSDLPanAttachment(NSDLPanAttachment NSDLPanAttachment);

	public boolean updateNSDLPanAttachment(NSDLPanAttachment NSDLPanAttachment);

	public List<NSDLPanAttachment> getNSDLPanAttachmentByNamedQuery(String query, Map<String, Object> param);

	public NSDLPanAttachment getNSDLPanAttachmentByApId(int id);

}
