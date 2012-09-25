package org.northstar.bricks.test;

import com.orientechnologies.orient.core.annotation.OId;

/**
 * Created with IntelliJ IDEA.
 * User: northstar
 * Date: 12-9-25
 * Time: 下午4:10
 * To change this template use File | Settings | File Templates.
 */
public class Enterprise {
    @OId
    private Long rid;

    private String name;
    private String address;
    private String legalAgent;
    private String safetyChief;
    private String safetyManager;
}
