/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package config;

import javax.inject.Named;
import javax.enterprise.context.ApplicationScoped;
import javax.security.enterprise.identitystore.DatabaseIdentityStoreDefinition;
import javax.security.enterprise.identitystore.Pbkdf2PasswordHash;

/**
 *
 * @author leap
 */
@DatabaseIdentityStoreDefinition(
    dataSourceLookup = "sgadbRes",
    callerQuery = "select password from user_tb where name=?",
    groupsQuery = "select groupname from group_master where username=?",
    hashAlgorithm = Pbkdf2PasswordHash.class,
    priority = 30
)
@ApplicationScoped
public class Project {

    /**
     * Creates a new instance of Project
     */
    public Project() {
    }
    
}
