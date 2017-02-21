package jinhe.lt.ldap.novell.test;


/*******************************************************************************
 * $name:         NameAndOID.java
 * $description:  NameAndOID.java shows how to get an oid from its name or get
 *                the name from an OID. A single Novell eDirectory syntax may 
 *                have multiple matches of LDAP syntaxes. NameAndOID.java does 
 *                a table lookup.
 *
 *                The names and oids are defined in:
 *                  1. LDAP RFC 2252: Lightweight Directory Access Protocol(v3)
 *                  2. Internet Draft: draft-sermersheim-nds-ldap-schema-**.txt
 *                  3. Internet Draft: draft-khan-ldapext-replica-mgmt-**.txt
 *                  4. Internet Draft: draft-rharrision-lburp-**.txt
 *                  5. Internet Draft: draft-smith-psearch-ldap-**.txt
 *                  6. Internet Draft: draft-ietf-ldapext-ldapv3-vlv-**.txt
 * $sample runs:
 *                $ java NameAndOID "syn_stream"
 *                OID: 1.3.6.1.4.1.1466.115.121.1.5
 *                Descriptive name: Binary
 *                Novell eDirectory syntax name: SYN_STREAM
 *                Ref.: RFC 2252: Lightweight Directory Access Protocol(v3)
 *
 *                $ java NameAndOID "directory string"
 *                OID: 1.3.6.1.4.1.1466.115.121.1.15
 *                Descriptive name: Directory String
 *                Novell eDirectory syntax name: SYN_CI_STRING
 *                Ref.: RFC 2252: Lightweight Directory Access Protocol(v3)
 *
 *                $ java NameAndOID "ADD_REPLICA_REQ"
 *                OID: 2.16.840.1.113719.1.27.100.7
 *                Descriptive name: Add Replica Request
 *                Ref.: Internet Draft: draft-khan-ldapext-replica-mgmt-**.txt
 *
 *                $ java NameAndOID "Persistent Search"
 *                OID: 2.16.840.1.113730.3.4.3
 *                Descriptive name: Persistent Search
 *                Ref.: Internet Draft: draft-ietf-ldapext-psearch-**.txt
 *
 *                $ java NameAndOID "1.3.6.1.4.1.1466.115.121.1.1"
 *                OID: 1.3.6.1.4.1.1466.115.121.1.1
 *                Descriptive name: ACI Item
 *                Novell eDirectory syntax name: SYN_OCTET_STRING
 *                Ref.: RFC 2252: Lightweight Directory Access Protocol(v3     
 ******************************************************************************/

public class NameAndOID implements LDAPOIDs {
    
    private static int found = 0;
    private static int type  = 0;

    public static void main( String[] args ) {      
                       
        if (args.length != 1) {
            usage();
            System.exit(-1);
        }

        String value =  args[0];
        
        if (value.length() == 0) {
            usage();
            System.exit(-1);
        }
        else {
            NameAndOID no = new NameAndOID(); 
            no.getOidInfo(value);

            if (found == 0 ) {
                System.out.println(type == 0? "Unknown name: " + value:
                                              "Unknown oid: " + value);
            }
        }
        
        System.exit(0);
    }

    public static void usage() 
    {
        System.out.println("Usage:   java NameAndOID <name/oid>\n");
        System.out.println("Example: java NameAndOID \"SYN_STREAM\"");
        System.out.println("or");
        System.out.println("Example: java NameAndOID \"Directory String\"");
        System.out.println("or");
        System.out.println("Example: java NameAndOID \"ADD_REPLICA_REQ\"");
        System.out.println("or");
        System.out.println("Example: java NameAndOID \"Persistant Search\"");
        System.out.println("or");
        System.out.println("Example: java NameAndOID " 
                                    + "\"1.3.6.1.4.1.1466.115.121.1.1\"");
        System.out.println("Note:    name can be an LDAP syntax name, a Novell" 
                     + " syntax name, a\n         control name, or a extension"
                     + " name supported by Novell eDirectory");
    }       
         
    public void getOidInfo(String value) {
        
        int i, len = NamesAndOIDs.length;
        
        for (i = 0; i < value.length(); i++) {
            if(!(Character.isDigit(value.charAt(i))) && (value.charAt(i)!= '.')) {
                type = 0;
                break;
            }
            else           
                type = 1;                
        }

        if(type == 0) {// from name to oid

            for  (i = 0;i < len; i++) {
                if ( (value.equalsIgnoreCase(NamesAndOIDs[i][OIDDescrIndex]))
                   ||(value.equalsIgnoreCase(NamesAndOIDs[i][OIDNameIndex]))) {
                    found = 1;
                    printNameAndOID(i);
                }
            }
        }
        else {// from oid to name

            for (i = 0; i < len; i++) {
                if (value.equalsIgnoreCase(NamesAndOIDs[i][OIDValueIndex])) {
                    found = 1;
                    printNameAndOID(i);
                }
            }
        }
    }
     
    public static void printNameAndOID(int index) {

        String dName = NamesAndOIDs[index][OIDDescrIndex];
        String sName = NamesAndOIDs[index][OIDNameIndex];        
        String oid   = NamesAndOIDs[index][OIDValueIndex];
        String ref   = NamesAndOIDs[index][OIDRefIndex];
                
        if (oid.length() != 0)
            System.out.println("        OID: " + oid);
        if (dName.length() != 0)
            System.out.println("        Descriptive name: " + dName);
        if (sName.length() != 0)
            System.out.println("        Novell eDirectory syntax name: "+sName);               
        if (ref.length() != 0)
            System.out.println("        Ref.: " + ref);
        
        System.out.println("");
    }
}


