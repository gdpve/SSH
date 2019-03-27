package cn.itcast.dao.impl;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
//HibernateDaoSupport 为dao注入sessionFactory

@Repository
public class UserDaoImpl extends HibernateDaoSupport implements UserDao {
	
	@Resource//根据类型注入spring工厂中的会话工厂对象sessionFactory
	public void setMySessionFactory(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}
	
    public User getByUserCode( String usercode) {

    /*	hql的方式
     * 
     *      User u=new User();    
            u.setUser_code(usercode);    
	     	return (User) this.getHibernateTemplate().findByExample(u).get(0);//获取一个对象就用get（0）的方式  find都是查询集合
     */	
    	//Criteria
    	  /*  DetachedCriteria dc = DetachedCriteria.forClass(User.class); //这个离线查询对象是mysql中的Example差不多就是专门设置查询用的
    		 dc.add(Restrictions.eq("user_code",usercode));	
    			List<User> list = (List<User>) getHibernateTemplate().findByCriteria(dc);//提供了专门的离线查询的方式
    				
    			if(list != null && list.size()>0){
    				return list.get(0);
    			}else{
    				return null;
    			}*/
    	  
    	 //原生SQL查询
//    			List<Object[]> list = getHibernateTemplate().execute(new HibernateCallback<List>() {
//    				String sql = " SELECT * from sys_user where user="+usercode;
//    				@Override
//    				public List doInHibernate(Session session) throws HibernateException {
//    					SQLQuery query = session.createSQLQuery(sql);
//    					return query.list();
//    				}
//    			});
//    			 User u= list.get(0);
//    			return list.get(0);
//    	
    
    		String sql = "SELECT * from sys_user where user_code="+"'"+usercode+"'";
    		System.out.println(sql);
    		Session session = getHibernateTemplate().getSessionFactory().getCurrentSession();
    		Query query = session.createSQLQuery(sql).addEntity(User.class); 
    	    List<User> list=query.list();
    	    User user=list.get(0);
    	    return user;

    	}
		
	

	@Override
	public void save(User u) {
		getHibernateTemplate().save(u);
	}
	

}
