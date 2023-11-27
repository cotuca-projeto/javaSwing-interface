package database.dbos;

public class User implements Cloneable
{
    private int    id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    // private String password;
    private  byte  profile_image;
    private String createdAt;
    private String updatedAt;
 
    public void setId (int id) throws Exception
    {
        if (id <= 0)
            throw new Exception ("id invalido");

        this.id = id;
    }   

    public void setFirstName (String firstName) throws Exception
    {
        if (firstName==null || firstName.equals(""))
            throw new Exception ("Nome nao fornecido");

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // public void setPassword(String password) {
    //     this.password = password;
    // }

    public void setProfile_image(byte profile_image) {
        this.profile_image = profile_image;
    }
    
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

   
    public int getId ()
    {
        return this.id;
    }

    public String getFirstName ()
    {
        return this.firstName;
    }

    public String getLastName() {
       return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    // public String getPassword() {
    //     return password;
    // }

    public byte getProfile_image() {
        return profile_image;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

     public User() {
    }

    public User (int id, String firstName, String lastName, String email, String username, String password, byte profile_image, String created, String updated) throws Exception
    {
        this.setId (id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setUsername(username);
        // this.setPassword(password);
        this.setProfile_image(profile_image);
        this.setCreatedAt(created);
        this.setUpdatedAt(updated);
        
    }

    @Override
    public String toString ()
    {
        String ret= "\n";

        ret+="id....: "+this.id+"\n";
        ret+="Nome..: "+this.firstName  +"\n";
        ret+="LastN.: "+this.lastName + "\n";
        ret+="User..: "+this.username  +"\n";
        ret+="Email.: "+this.email+"\n";
        // ret+="Pass..: "+this.password + "\n";
        ret+="Image.: "+this.profile_image + "\n";
        ret+="Crea..: "+this.createdAt  +"\n";
        ret+="Upda..: "+this.updatedAt;

        return ret;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (this==obj)
            return true;

        if (obj==null)
            return false;

        if (!(obj instanceof User))
            return false;

        User p = (User)obj;

        if (this.id!=p.id)
            return false;

        if (this.id != p.id || this.firstName != p.firstName ||
            this.lastName != p.lastName || this.email != p.email ||
            this.username != p.username /*|| this.password != p.password*/)
            return false;

        return true;
    }

    @Override
    public int hashCode ()
    {
        int ret=666;

        ret = 7*ret +  Integer.valueOf(this.id).hashCode();
        ret = 7*ret +  String.valueOf(this.firstName).hashCode();
        ret = 7*ret +  String.valueOf(this.lastName).hashCode();
        ret = 7*ret +  String.valueOf(this.email).hashCode();
        ret = 7*ret +  String.valueOf(this.username).hashCode();
        // ret = 7*ret +  String.valueOf(this.password).hashCode();
        ret = 7*ret +  Byte.valueOf(this.profile_image).hashCode();

        if (ret<0) ret = -ret;

        return ret;
    }


    public User (User modelo) throws Exception
    {
        this.id = modelo.id; // nao clono, pq nao eh objeto
        this.firstName   = modelo.firstName;   // nao clono, pq nao eh clonavel
        this.lastName = modelo.lastName;
        this.email = modelo.email;
        this.username = modelo.username;
        // this.password = modelo.password;
        this.profile_image = modelo.profile_image;
    }

    @Override
    public Object clone ()
    {
        User ret=null;

        try
        {
            ret = new User (this);
        }
        catch (Exception erro)
        {} // nao trato, pq this nunca � null e construtor de
           // copia da excecao qdo seu parametro for null

        return ret;
    }
}