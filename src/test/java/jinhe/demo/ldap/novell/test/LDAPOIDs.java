package jinhe.lt.ldap.novell.test;

public interface  LDAPOIDs {
  static String RFC1   = "RFC 2252: Lightweight Directory Access Protocol(v3)";
  static String RFC2   = "RFC 2891: LDAP Control for Server Side Sorting of Search Results";
  static String RFC3   = "RFC 3296: Named Subordinate References in LDAP";
  static String Draft1 = "Internet Draft: draft-sermersheim-nds-ldap-schema-**.txt";
  static String Draft2 = "Internet Draft: draft-khan-ldapext-replica-mgmt-**.txt";
  static String Draft3 = "Internet Draft: draft-rharrision-lburp-**.txt";
  static String Draft4 = "Internet Draft: draft-ietf-ldapext-psearch-**.txt";
  static String Draft5 = "Internet Draft: draft-ietf-ldapext-ldapv3-vlv-**.txt";

 // Each line in the NamesAndOIDs array has five fields. The fields are:

 //     field 1: type of the line. s-syntax, e-extension, and c-control.

 //     field 2: description of the operation represented by the oid.

 //     field 3: NDS syntax name.

 //     field 4: oid value.

 //     field 5: IETF document reference.

  static String OIDTypeSyntax    = "s";
  static String OIDTypeExtension = "e";
  static String OIDTypeControl   = "c";

  static int OIDTypeIndex  = 0;  // Field 1: type

  static int OIDDescrIndex = 1;  // Field 2: Description

  static int OIDNameIndex  = 2;  // Field 3: Name

  static int OIDValueIndex = 3;  // Field 4: OID Value

  static int OIDRefIndex   = 4;  // Field 5: Reference


  static String[][] NamesAndOIDs =
  {
   // defined in "RFC 2252: Lightweight Directory Access Protocol (v3)"

    {"s","ACI Item","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.1",RFC1},
    {"s","Access Point","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.2",RFC1},
    {"s","Attribute Type Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.3",RFC1},
    {"s","Audio","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.4",RFC1},
    {"s","Binary","SYN_STREAM","1.3.6.1.4.1.1466.115.121.1.5",RFC1},
    {"s","Bit String","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.6",RFC1},
    {"s","Boolean","SYN_BOOLEAN","1.3.6.1.4.1.1466.115.121.1.7",RFC1},
    {"s","Certificate","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.8",RFC1},
    {"s","Certificate List","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.9",RFC1},
    {"s","Certificate Pair","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.10",RFC1},
    {"s","Country String","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.11",RFC1},
    {"s","DN","SYN_DIST_NAME","1.3.6.1.4.1.1466.115.121.1.12",RFC1},
    {"s","Data Quality Syntax","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.13",RFC1},
    {"s","Delivery Method","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.14",RFC1},
    {"s","Directory String","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.15",RFC1},
    {"s","DIT Content Rule Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.16",RFC1},
    {"s","DIT Structure Rule Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.17",RFC1},
    {"s","DL Submit Permission","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.18",RFC1},
    {"s","DSA Quality Syntax","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.19",RFC1},
    {"s","DSE Type","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.20",RFC1},
    {"s","Enhanced Guide","SYN_INTEGER","1.3.6.1.4.1.1466.115.121.1.21",RFC1},
    {"s","Facsimile Telephone Number","SYN_FAX_NUMBER","1.3.6.1.4.1.1466.115.121.1.22",RFC1},
    {"s","Fax","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.23",RFC1},
    {"s","Generalized Time","SYN_TIME","1.3.6.1.4.1.1466.115.121.1.24",RFC1},
    {"s","Guide","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.25",RFC1},
    {"s","IA5 String","SYN_CE_STRING","1.3.6.1.4.1.1466.115.121.1.26",RFC1},
    {"s","INTEGER","SYN_INTEGER","1.3.6.1.4.1.1466.115.121.1.27",RFC1},
    {"s","INTEGER","SYN_INTERVAL","1.3.6.1.4.1.1466.115.121.1.27",RFC1},
    {"s","JPEG","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.28",RFC1},
    {"s","LDAP Syntax Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.54",RFC1},
    {"s","LDAP Schema Definition","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.56",RFC1},
    {"s","LDAP Schema Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.57",RFC1},
    {"s","Master And Shadow Access Points","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.29",RFC1},
    {"s","Matching Rule Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.30",RFC1},
    {"s","Matching Rule Use Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.31",RFC1},
    {"s","Mail Preference","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.32",RFC1},
    {"s","MHS OR Address","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.33",RFC1},
    {"s","Modify Rights","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.55",RFC1},
    {"s","Name And Optional UID","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.34",RFC1},
    {"s","Name Form Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.35",RFC1},
    {"s","Numeric String","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.36",RFC1},
    {"s","Object Class Description","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.37",RFC1},
    {"s","Octet String","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.40",RFC1},
    {"s","OID","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.38",RFC1},
    {"s","Other Mailbox","SYN_CI_STRING","1.3.6.1.4.1.1466.115.121.1.39",RFC1},
    {"s","Postal Address","SYN_PO_ADDRESS","1.3.6.1.4.1.1466.115.121.1.41",RFC1},
    {"s","Protocol Information","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.42",RFC1},
    {"s","Presentation Address","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.43",RFC1},
    {"s","Printable String","SYN_PR_STRING","1.3.6.1.4.1.1466.115.121.1.44",RFC1},
    {"s","Substring Assertion","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.58",RFC1},
    {"s","Subtree Specification","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.45",RFC1},
    {"s","Supplier Information","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.46",RFC1},
    {"s","Supplier Or Consumer","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.47",RFC1},
    {"s","Supplier And Consumer","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.48",RFC1},
    {"s","Supported Algorithm","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.49",RFC1},
    {"s","Telephone Number","SYN_TEL_NUMBER","1.3.6.1.4.1.1466.115.121.1.50",RFC1},
    {"s","Teletex Terminal Identifier","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.51",RFC1},
    {"s","Telex Number","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.52",RFC1},
    {"s","UTC Time","SYN_OCTET_STRING","1.3.6.1.4.1.1466.115.121.1.53",RFC1},
   // defined in RFC 3296: Named Subordinate References in LDAP

    {"c","Server Side Sort Control Request","","1.2.840.113556.1.4.473", RFC2},
    {"c","Server Side Sort Control Response","","1.2.840.113556.1.4.474", RFC2},
   // defined in RFC 3296: Named Subordinate References in LDAP

    {"c","ManageDsaIT Control Request","","2.16.840.1.113730.3.4.2", RFC3},
   // defined in Internet Draft: draft-sermersheim-nds-ldap-schema-**.txt"

    {"s","Case Ignore List","SYN_CI_LIST","2.16.840.1.113719.1.1.5.1.6",Draft1},
    {"s","Tagged Data","SYN_NET_ADDRESS","2.16.840.1.113719.1.1.5.1.12",Draft1},
    {"s","Octet List","SYN_OCTET_LIST","2.16.840.1.113719.1.1.5.1.13",Draft1},
    {"s","Tagged String","SYN_EMAIL_ADDRESS","2.16.840.1.113719.1.1.5.1.14",Draft1},
    {"s","Tagged Name and String","SYN_PATH","2.16.840.1.113719.1.1.5.1.15",Draft1},
    {"s","NDS Replica Pointer","SYN_REPLICA_POINTER","2.16.840.1.113719.1.1.5.1.16",Draft1},
    {"s","ACL","SYN_OBJECT_ACL","2.16.840.1.113719.1.1.5.1.17",Draft1},
    {"s","NDS Timestamp","SYN_TIMESTAMP","2.16.840.1.113719.1.1.5.1.19",Draft1},
    {"s","Counter","SYN_COUNTER","2.16.840.1.113719.1.1.5.1.22",Draft1},
    {"s","Tagged Name","SYN_BACK_LINK","2.16.840.1.113719.1.1.5.1.23",Draft1},
    {"s","Typed Name","SYN_TYPED_NAME","2.16.840.1.113719.1.1.5.1.25",Draft1},
   // defined in Internet Draft: draft-khan-ldapext-replica-mgmt-**.txt"

    {"e","ndsToLdapResponse","","2.16.840.1.113719.1.27.100.1",Draft2},
    {"e","ndsToLdapRequest","","2.16.840.1.113719.1.27.100.2",Draft2},
    {"e","Split Partition Request","","2.16.840.1.113719.1.27.100.3",Draft2},
    {"e","Split Partition Response","","2.16.840.1.113719.1.27.100.4",Draft2},
    {"e","Merge Partition Request","","2.16.840.1.113719.1.27.100.5",Draft2},
    {"e","Merge Partition Response","","2.16.840.1.113719.1.27.100.6",Draft2},
    {"e","Add Replica Request","","2.16.840.1.113719.1.27.100.7",Draft2},
    {"e","Add Replica Response","","2.16.840.1.113719.1.27.100.8",Draft2},
    {"e","Refresh Server Request","","2.16.840.1.113719.1.27.100.9",Draft2},
    {"e","Refresh Server Response","","2.16.840.1.113719.1.27.100.10",Draft2},
    {"e","Delete Replica Request","","2.16.840.1.113719.1.27.100.11",Draft2},
    {"e","Delete Replica Response","","2.16.840.1.113719.1.27.100.12",Draft2},
    {"e","Partition Entry Count Request","","2.16.840.1.113719.1.27.100.13",Draft2},
    {"e","Partition Entry Count Response","","2.16.840.1.113719.1.27.100.14",Draft2},
    {"e","Change Replica Type Request","","2.16.840.1.113719.1.27.100.15",Draft2},
    {"e","Change Replica Type Response","","2.16.840.1.113719.1.27.100.16",Draft2},
    {"e","Get Replica Info Request","","2.16.840.1.113719.1.27.100.17",Draft2},
    {"e","Get Replica Info Response","","2.16.840.1.113719.1.27.100.18",Draft2},
    {"e","List Replicas Request","","2.16.840.1.113719.1.27.100.19",Draft2},
    {"e","List Replicas Response","","2.16.840.1.113719.1.27.100.20",Draft2},
    {"e","Receive All Updates Request","","2.16.840.1.113719.1.27.100.21",Draft2},
    {"e","Receive All Updates Response","","2.16.840.1.113719.1.27.100.22",Draft2},
    {"e","Send All Updates Request","","2.16.840.1.113719.1.27.100.23",Draft2},
    {"e","Send All Updates Response","","2.16.840.1.113719.1.27.100.24",Draft2},
    {"e","Partition Sync Request","","2.16.840.1.113719.1.27.100.25",Draft2},
    {"e","Partition Sync Response","","2.16.840.1.113719.1.27.100.26",Draft2},
    {"e","Schema Sync Request","","2.16.840.1.113719.1.27.100.27",Draft2},
    {"e","Schema Sync Response","","2.16.840.1.113719.1.27.100.28",Draft2},
    {"e","Abort Partition Operation Request","","2.16.840.1.113719.1.27.100.29",Draft2},
    {"e","Abort Partition Operation Response","","2.16.840.1.113719.1.27.100.30",Draft2},
    {"e","Get Bind DN Request","","2.16.840.1.113719.1.27.100.31",Draft2},
    {"e","Get Bind DN Response","","2.16.840.1.113719.1.27.100.32",Draft2},
    {"e","Get Effective Privileges Request","","2.16.840.1.113719.1.27.100.33",Draft2},
    {"e","Get Effective Privileges Response","","2.16.840.1.113719.1.27.100.34",Draft2},
    {"e","Set Replication Filter Request","","2.16.840.1.113719.1.27.100.35",Draft2},
    {"e","Set Replication Filter Response","","2.16.840.1.113719.1.27.100.36",Draft2},
    {"e","Get Replication Filter Request","","2.16.840.1.113719.1.27.100.37",Draft2},
    {"e","Get Replication Filter Response","","2.16.840.1.113719.1.27.100.38",Draft2},
    {"e","Create Orphan Partition Request","","2.16.840.1.113719.1.27.100.39",Draft2},
    {"e","Create Orphan Partition Response","","2.16.840.1.113719.1.27.100.40",Draft2},
    {"e","Remove Orphan Partition Request","","2.16.840.1.113719.1.27.100.41",Draft2},
    {"e","Remove Orphan Partition Response","","2.16.840.1.113719.1.27.100.42",Draft2},
    {"e","Trigger Back Linker Request","","2.16.840.1.113719.1.27.100.43",Draft2},
    {"e","Trigger Back Linker Response","","2.16.840.1.113719.1.27.100.44",Draft2},
   // the following two extensions are not yet documentated

    {"e","Trigger DRL process Request","","2.16.840.1.113719.1.27.100.45",""},
    {"e","Trigger DRL process Response","","2.16.840.1.113719.1.27.100.46",""},
    {"e","Trigger Janitor Request","","2.16.840.1.113719.1.27.100.47",Draft2},
    {"e","Trigger Janitor Response","","2.16.840.1.113719.1.27.100.48",Draft2},
    {"e","Trigger Limber Request","","2.16.840.1.113719.1.27.100.49",Draft2},
    {"e","Trigger Limber Response","","2.16.840.1.113719.1.27.100.50",Draft2},
    {"e","Trigger Skulker Request","","2.16.840.1.113719.1.27.100.51",Draft2},
    {"e","Trigger Skulker Response","","2.16.840.1.113719.1.27.100.52",Draft2},
    {"e","Trigger Schema Sync Request","","2.16.840.1.113719.1.27.100.53",Draft2},
    {"e","Trigger Schema Sync Response","","2.16.840.1.113719.1.27.100.54",Draft2},
    {"e","Trigger Partition Purge Request","","2.16.840.1.113719.1.27.100.55",Draft2},
    {"e","Trigger Partition Purge Response","","2.16.840.1.113719.1.27.100.56",Draft2},
   // defined in Internet Draft: draft-rharrision-lburp-**.txt"

    {"e","Start LBURP Request","","2.16.840.1.113719.1.142.100.1",Draft3},
    {"e","Start LBURP Response","","2.16.840.1.113719.1.142.100.2",Draft3},
    {"e","End LBURP Request","","2.16.840.1.113719.1.142.100.4",Draft3},
    {"e","End LBURP Response","","2.16.840.1.113719.1.142.100.5",Draft3},
    {"e","LBURP Operation","","2.16.840.1.113719.1.142.100.6", Draft3},
    {"e","LBURP Operation Done","","2.16.840.1.113719.1.142.100.7",Draft3},
   // the following two controls are not yet documented

    {"c","Simple Password","","2.16.840.1.113719.1.27.101.5",""},
    {"c","Forward Reference","","2.16.840.1.113719.1.27.101.6",""},
   // defined in Internet Draft: draft-ietf-ldapext-psearch-**.txt"

    {"c","Persistent Search","","2.16.840.1.113730.3.4.3", Draft4},
    {"c","Entry Change Notification","","2.16.840.1.113730.3.4.7",Draft4},
   // defined in Internet Draft: draft-ietf-ldapext-ldapv3-vlv-**.txt

    {"c","VLV Control Request","","2.16.840.1.113730.3.4.9",Draft5},
    {"c","VLV Control Response","","2.16.840.1.113730.3.4.10", Draft5},
  };
}


