[![Build Status](https://travis-ci.org/andreacomo/tomcat-bcrypt.svg?branch=master)](https://travis-ci.org/andreacomo/tomcat-bcrypt)


# Tomcat BCrypt

Want to use *[BCrypt](https://it.wikipedia.org/wiki/Bcrypt) hashed password* with your preferred Tomcat (**8 or above**) Realm?

This is an implementation of Tomcat `CredentialHandler` wrapping [jBCrypt](https://www.mindrot.org/projects/jBCrypt/) (0.4), Java implementation of bcrypt algorithm.

## How to use

* Download [latest jar](https://search.maven.org/remotecontent?filepath=it/cosenonjaviste/tomcat-bcrypt/1.0.0/tomcat-bcrypt-1.0.0.jar)
* Copy to `TOMCAT_HOME/lib` folder
* Nest `BCryptoCredentialHandler` in your preferred Realm, for example:

```xml
<Context>
    <Realm className="org.apache.catalina.realm.JDBCRealm"
        [...]
        >
        <CredentialHandler className="it.cosenonjaviste.tomcat.BCryptoCredentialHandler"/>
    </Realm>
</Context>
```
