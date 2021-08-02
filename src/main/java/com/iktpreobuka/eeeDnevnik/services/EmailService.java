package com.iktpreobuka.eeeDnevnik.services;

import com.iktpreobuka.eeeDnevnik.models.EmailObject;

public interface EmailService {
	
	void sendSimpleMessage(EmailObject object);
	void sendTemplateMessage(EmailObject object) throws Exception;

}
