package jinhe.lt.ldap.novell.test;

/*******************************************************************************
 * $name:        DynamicGroup.java
 *
 * $description: The DynamicGroup.java sample demonstrates how to:
 *  1) create the Dynamic Group Entry cn=myDynamicGroup
 *     in a specified container with a specified memberQueryURL
 *  2) read and print the values of the "member" attribute of cn=myDynamicGroup
 *  3) delete the dynamic group
 *
 *  Notes on Dynamic Groups:
 *
 *  Dynamic groups are supported in Novell eDirectory version 8.6.1 or later.
 *
 *  A dynamic group is similar to a group entry, but has a search URL
 *  attribute.  Entries satisfying the search URL are considered members of
 *  the group. The DN of each member will be returned when reading the
 *  "member" (or its synonym "uniqueMember") attribute.
 *
 *  A dynamic group is created either with objectClass="dynamicGroup" or by
 *  adding the auxiliary class "dynamicGroupAux" to an existing group entry.
 *
 *  The search URL is specified in the "memberQueryURL" attribute.
 *  The value of "memberQueryURL" is encoded as an "LDAP URL".
 *
 *  An entry may be statically added to a dynamic group by adding its DN
 *  to the "member" (or its synonym "uniqueMember") attribute.  Similarly
 *  an entry may be statically excluded from the group by adding its DN to the
 *  "excludedMember" attribute. These entries will be included or excluded
 *  from the group regardless of the search URL.
 *
 *  Note: at the present time, the only way to view only the static members
 *  of a dynamic group is to delete the memberQueryURL attribute and
 *  then read the member attribute.
 *
 *  In order to provide consistent results when processing the search URL,
 *  the authorization identity DN used to determine group membership is based
 *  on the following criteria:
 *  1) If the "dgIdentity" attribute is present, its value is the identity DN.
 *  2) If the above is false, and if the dynamic group entry contains a
 *     public/private key, then the DN of the group entry is the identity DN.
 *  3) If neither of the above are true, then the anonymous identity is the
 *     identity DN.
 *
 *  The creator of the group cannot set the "dgIdentity" attribute to a DN
 *  to which he or she does not already have rights.  The dynamic group entry
 *  and the DN specified by dgIdentity must be on the same server.
 *
 *  The "dgAllowDuplicates" attribute enables or disables the presence
 *  of duplicate values in the "membership" attribute. The default is false.
 *  Setting this attribute to true results in a faster search, but some values
 *  in the "membership" attribute may be duplicates.
 *
 *  The "dgTimeout" attribute determines the number of seconds to wait to get
 *  results from another server during dynamic group member search, when the
 *  search spans multiple servers.
 *
 *  The format the search URL in the memberQueryUrl attribute is:
 *      ldap://<base dn>??<scope>?<filter>[?[!]x-chain]

 *
 *  The optional extension "x-chain" causes the server to chain to other
 *  servers if necessary to complete the search. When present, the search
 *  will NOT be limited to the host server.  This extension should be used
 *  carefully. The exclamation indicates it is a critical extension, and if set
 *  the server will return an error if chaining is not supported or enabled.
 *
 *  For example, to create a dynamic group consisting of all entries in the
 *  "ou=sales,o=acme" subtree with the title "Manager", set memberQueryURL to:
 *      "ldap://ou=sales,o=acme??sub?(title=Manager)"

 *
 ******************************************************************************/
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPSearchConstraints;
import com.novell.ldap.LDAPSearchResults;

@SuppressWarnings("unchecked")
public class DynamicGroup
{
    public static void main( String[] args )
    {
    	String ldapHost = "192.168.0.6";
		int ldapPort = 389;// LDAPConnection.DEFAULT_PORT;
		int ldapVersion = 3;// LDAPConnection.LDAP_V3;
		String loginDN = "admin_hz";
		String password = "password";
       
        String containerName = "OU=仙居,OU=台州,O=ZJCZ";
        String queryURL = args[4];

        String dn = "cn=myDynamicGroup," + containerName;

        LDAPConnection lc = new LDAPConnection();

        try {
            lc.connect( ldapHost, ldapPort );
         
            lc.bind( ldapVersion, loginDN, password.getBytes("UTF8") );

            System.out.println( "\tAdding dynamic group entry...");

            /* add a dynamic group entry to the directory tree in the
             * specified container
             */
            addDynamicGroupEntry( lc, loginDN, dn, queryURL);

            /* 
             * Reading the member attribute of dynamic group entry and printing the values
             */
            System.out.println("\n\tReading the \"member\" " + " attribute of dynamic group ojbect ...");
            searchDynamicGroupEntry ( lc, dn );

           // Removing the dynamic group entry from the specified container

            System.out.println("\n\tDeleting dynamic group entry...");
            deleteDynamicGroupEntry( lc, dn );

            lc.disconnect();
        }
        catch( LDAPException e ) {
            System.out.println( "Error: " + e.toString() );
        }
        catch( UnsupportedEncodingException e ) {
            System.out.println( "Error: " + e.toString() );
        }
        System.exit(0);
    }


   // add dynamic group entry

    public static boolean addDynamicGroupEntry ( LDAPConnection lc,
                          String loginDN, String entryDN, String queryURL) {

        boolean status = true;
        LDAPAttributeSet  attributeSet = new LDAPAttributeSet();

       //The objectclass "dynamicGroup is used to create dynamic group entries

        attributeSet.add( new LDAPAttribute( "objectclass", "dynamicGroup" ));

        /* The memberQueryURL attribute describes the membership of the list
         * using an LDAPURL, which is defined in RFC2255
         */
        attributeSet.add( new LDAPAttribute( "memberQueryURL", queryURL ) );

        /* Set the identity to use for the implied search.  loginDN is used
         * as the dgIdentity in this sample.
         */
        attributeSet.add( new LDAPAttribute( "dgIdentity", loginDN ) );

        LDAPEntry newEntry = new LDAPEntry( entryDN, attributeSet );

        try {
            lc.add( newEntry );
            System.out.println("\tEntry: " + entryDN + " added successfully." );
        }
        catch( LDAPException e ) {
            System.out.println( "\t\tFailed to add dynamic group entry " + entryDN);
            System.out.println( "Error: " + e.toString() );
            status = false;
        }
        return status;
    }

   // read and print search results

    public static boolean searchDynamicGroupEntry ( LDAPConnection lc,
                                                     String searchBase ) {
        boolean status = true;
        int searchScope = LDAPConnection.SCOPE_BASE;
        String[] attrList = new String[]{"member"};
        String searchFilter = "(objectclass=*)";


       /* Since reading members of a dynamic group could potentially involve
        * a significant directory search, we use a timeout. Setting
        * time out to 10 seconds
        */
        LDAPSearchConstraints cons = new LDAPSearchConstraints();
        cons.setTimeLimit( 10000 ) ;

        try {
            LDAPSearchResults searchResults =
                lc.search(  searchBase,
                            searchScope,
                            searchFilter,
                            attrList,         // return only "member" attr

                            false,            // return attrs and values

                            cons );           // time out value


            LDAPEntry nextEntry = null ;
           // Read and print search results.  We expect only one entry */

            if (( nextEntry = searchResults.next()) != null ) {
                LDAPAttributeSet attributeSet = nextEntry.getAttributeSet();
                Iterator allAttributes = attributeSet.iterator();

                if ( allAttributes.hasNext() ) {
                   // found member(s) in this group

                    LDAPAttribute attribute =
                                (LDAPAttribute)allAttributes.next();
                    String attributeName = attribute.getName();

                    Enumeration allValues = attribute.getStringValues();

                    if( allValues != null) {
                        while(allValues.hasMoreElements()) {
                            String Value = (String) allValues.nextElement();
                            System.out.println("            " + attributeName
                                                        + " : " + Value);
                        }
                    }
                }
                else {
                   // no member(s) found in this group

                    System.out.println("            No objects passed the "
                    + " memberQueryURL filter.\n            Hence, there "
                    + " are no members in this group.\n");
                }
            }
        }
        catch( LDAPException e ) {
            System.out.println( "Error: " + e.toString() );
            status = false;
        }
        return status;
    }

   // delete the  dynamic group entry

    public static boolean deleteDynamicGroupEntry( LDAPConnection lc,
                                                    String deleteDN ) {

        boolean status = true;

        try {
           // Deletes the entry from the directory

            lc.delete( deleteDN );
            System.out.println("\tEntry: " + deleteDN + " was deleted." );
        }
        catch( LDAPException e ) {
            System.out.println( "\t\tFailed to remove dynamic group entry." );
            System.out.println( "Error: " + e.toString() );
            status = false;
        }
        return status;
    }
}


