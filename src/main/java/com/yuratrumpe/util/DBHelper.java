package com.yuratrumpe.util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;

public interface DBHelper {

    void closeDBResources();

    Connection getConnection();

    Configuration getConfiguration();



}
