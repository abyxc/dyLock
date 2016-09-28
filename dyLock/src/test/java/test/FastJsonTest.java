package test;


import com.alibaba.fastjson.JSON;

public class FastJsonTest {

	public static void main(String[] args) {
		 // 构建用户geust  
        User guestUser = new User();  
        guestUser.setName("guest");  
        guestUser.setAge(28);  

        // 构建用户root  
        User rootUser = new User();  
        rootUser.setName("root");  
        guestUser.setAge(35);  
        
     // 构建用户组对象  
        UserGroup group = new UserGroup();  
        group.setAame("admin"); 
        group.getDate().add(rootUser);
        group.getDate().add(guestUser);
        
      
        
        
        // 用户组对象转JSON串  
        String jsonString = JSON.toJSONString(group);  
        System.out.println("jsonString:" + jsonString); 
        
        
//        User u = new User();
//        u.setAge(22);
//        u.setName("名字");
//        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(User.class, "name");
//        Assert.assertEquals("{\"name\":\"flym\"}", JSON.toJSONString(u, filter));
//        String text = "{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}";  
//        String s = "\"";
        
//        String text = "{\"age\":105,\"id\":\"testFastJson001\",\"name\":\"maks\"}";  
//        System.out.println("parseJsonObject()方法：json==" + JSON.parseObject(text));  
	}
}
