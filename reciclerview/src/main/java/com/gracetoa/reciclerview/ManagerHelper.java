package com.gracetoa.reciclerview;

import java.util.ArrayList;
import java.util.List;

public class ManagerHelper {

    public static  ManagerHelper shared = new ManagerHelper();

    public List<Contact> contacts = getContacts();
    private int counter ;


    public List<Contact> getContacts() {
        return new ArrayList<Contact>() {{
            add(new Contact("Lucas","Toa",22));
            add(new Contact("Nerea","Cadena",45));
            add(new Contact("Carlo","Constante",25));
            add(new Contact("Paquito","Inglada",18));
        }};
    }


    public void  addContact(int position){
        contacts.add(position, new Contact("New Name"+ " " + (+counter), "New Lastname", ++counter));

    }

    public void deleteContact(int position){
        contacts.remove(position);
    }
}
