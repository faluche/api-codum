#TODO 
- [ ] Change the field injection (@autowired) to constructor injection 
    ```java 
  @autowired
  private UserService userService;
  
  ------------to
  
  @RestController
  public class UserController{
    private UserService userService;
    public UserController(UserService userService){
    this.userService = userService;
    }
    ``` 

- [ ] make pagination with JPA --> [Baeldung](https://www.baeldung.com/spring-data-jpa-pagination-sorting)
- [ ] DB --> PostgreSQL [Config](https://mkyong.com/spring-boot/spring-boot-spring-data-jpa-postgresql/)
- [ ] learn how use mock and Bearer Token
- [ ] make different profile with different access  
