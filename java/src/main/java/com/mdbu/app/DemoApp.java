package com.mdbu.app;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import com.mdbu.aggregations.Aggregation;
import com.mdbu.crud.Crud;
import com.mdbu.transactions.Transaction;
import com.mdbu.utils.MongoClientSingleton;
import com.mongodb.client.MongoClient;
import org.slf4j.LoggerFactory;


public class DemoApp {
    public static void main(final String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger("org.mongodb.driver");
        // Available levels are: OFF, ERROR, WARN, INFO, DEBUG, TRACE, ALL
        root.setLevel(Level.WARN);

        MongoClient client = MongoClientSingleton.getClient();

        Crud crud = new Crud(client);
        crud.insertOneDocument();
        crud.insertManyDocuments();

        crud.findAllCheckingAccounts();
        crud.findAllCheckingAccounts();

        crud.updateOneDocument();
        crud.updateManyDocuments();

        crud.deleteDocument();

        //Transaction
        Transaction txn = new Transaction(client);
        txn.transferFunds();

        Aggregation agg = new Aggregation(client);
        agg.showAccountTypeSummary();
        agg.findAccountById("MDB727578791");
        agg.sortCheckingAccountsDescending();

        //Close the client
        client.close();
    }
}