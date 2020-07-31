package co.com.interkont.wscobra.auth;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;

@Service
public class LDAP  {
	
	@Autowired
	Environment entorno;
    
	private static String textError;
	
	public static int validarLDAP(String strUser, String strPassword, Environment env) {
		int validacion = 0; //Success
		
		
		//return true;
        	try {
        		
        		System.out.println("strUser="+strUser);
				System.out.println("password="+strPassword);

				System.out.println("env.getProperty(\"app.ldapIp\")="+env.getProperty("app.ldapIp"));
				System.out.println("env.getProperty(\"app.ldapNombre\")="+env.getProperty("app.ldapNombre"));
				System.out.println("env.getProperty(\"app.ldapConexion\")="+env.getProperty("app.ldapConexion"));
				System.out.println("env.getProperty(\"app.ldapSearch\")="+env.getProperty("app.ldapSearch"));
				
				
				StringBuilder login = new StringBuilder();
				StringBuilder ldapHost = new StringBuilder();
				
				
				ldapHost.append(env.getProperty("app.ldapIp"));

				login.append(env.getProperty("app.ldapNombre"));
				login.append("\\");
				login.append(strUser);
				
				
				LDAPConnection lc = new LDAPConnection();
				lc.connect(ldapHost.toString(), LDAPConnection.DEFAULT_PORT);
				lc.bind(LDAPConnection.LDAP_V3, login.toString(), strPassword.getBytes("UTF8"));

				Properties props = new Properties();

				props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
				props.put(Context.SECURITY_AUTHENTICATION, env.getProperty("app.ldapConexion"));
				props.put(Context.PROVIDER_URL, "ldap://" + ldapHost.append(":").append(LDAPConnection.DEFAULT_PORT));
				props.put(Context.SECURITY_PRINCIPAL, login.toString());
				props.put(Context.SECURITY_CREDENTIALS, strPassword.getBytes("UTF8"));

				InitialDirContext ctx = new InitialDirContext(props);
				

				NamingEnumeration<?> ne = ctx.search(env.getProperty("app.ldapSearch"), "(SAMAccountName=" + strUser + ")", new SearchControls());
				while (ne.hasMore()) {
				    SearchResult result = (SearchResult) ne.next();
				    Attributes attrs = result.getAttributes();
				    System.out.println(attrs.get("cn"));
				    
				}
				
				validacion = 0;
			}
			 catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (LDAPException e) {
				// TODO Auto-generated catch block
				LDAP.setError(e.getMessage());
				return e.getResultCode();
				
					
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return validacion;
	}
	
	public static void setError(String error) {
		textError = error;
	}
	
	public String getError() {
		return textError;
	}
	

}