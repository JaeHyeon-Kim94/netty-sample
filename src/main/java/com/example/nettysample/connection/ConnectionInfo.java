package com.example.nettysample.connection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class ConnectionInfo {

    private final String uniqueId;
    private final String ip;
    private final int port;

}
