package co.com.interkont.avanzame.auth.encrypt;

public enum AlgorithmEncrypter {

    DES("DES"),
    DESEDE("DESede"),
    BLOWFISH("Blowfish"),
    MD5_DES("PBEWithMD5AndDES"),
    MD5_TRIPLE_DES("PBEWithMD5AndTripleDES"),
    AES("AES");
                
    private String name;

    private AlgorithmEncrypter(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
