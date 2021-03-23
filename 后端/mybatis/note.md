# dao.xml与dao.java不在同一个class目录里面
```xml
<build>
    <resources>
      <resource>
        <directory>src/main/java</directory>
        <includes>
          <include>**/*.xml</include>
        </includes>
      </resource>
    </resources>
</build>
```