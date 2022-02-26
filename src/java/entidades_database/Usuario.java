package entidades_database;

public class Usuario  implements java.io.Serializable {


     private String cedula;
     private String nombre;
     private String apellido;
     private String tipousuario;
     private String email;
     private String telefono;
     private String contrasenna;

    public Usuario() {
    }

	
    public Usuario(String cedula) {
        this.cedula = cedula;
    }
    public Usuario(String cedula, String nombre, String apellido, String tipousuario, String email, String telefono, String contrasenna) {
       this.cedula = cedula;
       this.nombre = nombre;
       this.apellido = apellido;
       this.tipousuario = tipousuario;
       this.email = email;
       this.telefono = telefono;
       this.contrasenna = contrasenna;
    }
   
    public String getCedula() {
        return this.cedula;
    }
    
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getTipousuario() {
        return this.tipousuario;
    }
    
    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getContrasenna() {
        return this.contrasenna;
    }
    
    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }




}


