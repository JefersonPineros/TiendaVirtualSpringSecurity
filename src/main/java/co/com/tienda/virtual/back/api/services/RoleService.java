package co.com.tienda.virtual.back.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.tienda.virtual.back.api.entity.dao.IRoleDao;
import co.com.tienda.virtual.back.api.entity.models.Role;
@Service
public class RoleService implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	
	@Override
	public Role findById(Integer id) {
		return roleDao.findById(id).orElse(null);
	}

}
