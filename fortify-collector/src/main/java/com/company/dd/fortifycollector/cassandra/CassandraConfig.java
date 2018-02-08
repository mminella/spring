package com.company.dd.fortifycollector.cassandra;

/*@Configuration
//@EnableReactiveCassandraRepositories
public class CassandraConfig extends AbstractCassandraConfiguration  {

  @Value("${cassandra.contactpoints}")
  private String contactPoints;

  @Value("${cassandra.port}")
  private int port;

  @Value("${cassandra.keyspace}")
  private String keySpace;

  @Value("${cassandra.basePackages}")
  private String basePackages;

  @Value("${cassandra.username}")
  private String username;
  
  @Value("${cassandra.password}")
  private String password;
  
  
  @Override
  protected String getKeyspaceName() {
    return keySpace;
  }

  @Override
  protected String getContactPoints() {
    return contactPoints;
  }

  @Override
  protected int getPort() {
    return port;
  }
  
  @Override
  protected AuthProvider getAuthProvider(){
      return new PlainTextAuthProvider(username, password);
  }

  @Override
  public SchemaAction getSchemaAction() {
    return SchemaAction.NONE; //CREATE_IF_NOT_EXISTS;
  }

  @Override
  public String[] getEntityBasePackages() {
    return new String[] {basePackages};
  }
}*/