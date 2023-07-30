## 1.Illustrate
### 1.1Environmental configuration
* config [Canal](https://github.com/alibaba/canal) Environmental
  * database binlog 
  * canal config example instance...
* java 1.8+
* springboot version 2.3+
## 2.start using
###2.1 Import POM file
```xml
<dependency>
  <groupId>com.assionx</groupId>
  <artifactId>canal-sync-spring-boot-starter</artifactId>
  <version>1.1.3</version>
</dependency>

```

###2.2 example
####1.yml config
```yaml
spring:
  datasource:
    username: root
    password: 123456
    driver-class-name: com.mysql.jdbc.Driver
    url: jdbc:mysql://127.0.0.1:3306/xizheng?useUnicode=true&characterEncoding=utf8
canal:
  client:
    host: 127.0.0.1
    port: 11111
```
* @CanalBeanEntity(value = "canal_test") canal_test is database table name,
  this annotation  is CanalBean Handler target ,so it is important ... 
* CanalBean<CanalTest>  CanalTest is database table entity
```java
package com.example.handler;

import com.assionx.canal.client.annotation.CanalBeanEntity;

import com.assionx.canal.client.bean.CanalBean;
import com.example.entity.CanalTest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author assionx
 * @date 2023-07-28
 **/
@Component
@CanalBeanEntity(value = "canal_test")
@Slf4j
public class CanalTestHandler implements CanalBean<CanalTest> {
    @Override
    public boolean insert(CanalTest canalTest) {
        log.info("insert:{}",canalTest);
        return false;
    }

    @Override
    public boolean update(CanalTest canalTest, CanalTest t1) {
        log.info("before:{}",canalTest);
        log.info("after:{}",t1);
        return false;
    }

    @Override
    public boolean delete(CanalTest canalTest) {
        log.info("del:{}",canalTest);
        return false;
    }

}

```
* @TableName(value ="canal_test") belong to mybatis-plus annotation

* @Column(name = "create_time",type = ConventionCanalBeanField. Class)
   * ConventionCanalBeanField is CanalBeanField instance ,it  controls Convention variable assignment
   * name equals database table field name
```java
package com.example.entity;

import com.assionx.canal.client.annotation.Column;
import com.assionx.canal.client.bean.CanalBeanField;
import com.assionx.canal.client.bean.ConventionCanalBeanField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * canal_test
 * @TableName canal_test
 */
@TableName(value ="canal_test")
@Data
public class CanalTest implements Serializable {
    /**
     *
     */
    @TableId(type = IdType.AUTO)
    @Column(name = "id")
    private Integer id;

    /**
     *
     */
    @Column(name = "name")
    private String name;

    /**
     *
     */
    @Column(name ="status" )
    private Integer status;

    /**
     *
     */
    @Column(name = "create_time",type = ConventionCanalBeanField.class)
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
```
###then you can start your application and test