package dev.alsabea.services.impl;

import dev.alsabea.doas.ManagerDao;
import dev.alsabea.doas.impl.ManagerDaoImpl;
import dev.alsabea.entities.Manager;
import dev.alsabea.services.ManagerServices;

public class ManagerServicesImpl implements ManagerServices {
	
	
	private static ManagerServicesImpl mServ;
	
	private static ManagerDao mDao= ManagerDaoImpl.getInstance();
	
	
	public static ManagerServicesImpl getInstance() {
		
		if (mServ==null) 
			mServ= new ManagerServicesImpl();
		return mServ;
		
	}
	
	

	@Override
	public long createInstance(Manager t) {
		
		return mDao.createInstance(t);
	}

	@Override
	public Manager retrieveById(long key) {
		
		return mDao.retrieveById(key);
	}

	@Override
	public Manager retrieveByUsernameAndPassword(String username, String password) {
		return mDao.retrieveByUsernameAndPassword(username, password);
	}
	
	@Override
	public boolean update(Manager t) {
		return mDao.update(t);
	}

	@Override
	public boolean deleteById(long key) {
		
		return mDao.deleteById(key);
	}







}
