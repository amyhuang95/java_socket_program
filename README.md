# Simple Java Client-Server Program

This project implements a simple TCP server-client application in Java. The server listens for a client connection,
receives a text string from the client, processes it (by reversing the string and reversing its capitalization), and
sends the processed string back to the client. The client allows the user to input a short text (up to 80 characters), sends it to the server, and displays the
server's response. After showing the server response, the client and the server will both exit.

## Requirements

- Java Development Kit (JDK) 8 or higher.

## Instructions to Run

### 1. Compile the Code

Ensure you have both the `Server.java` and `Client.java` files in the same directory. Compile them using the `javac`
command:

```bash
javac Server.java Client.java
```

### 2. Run the Server

Start the server on a specific port (e.g., 32000). Open a terminal and execute:

```bash
java Server <port number>
```

Example:

```bash
java Server 32000
```

The server will begin listening on the specified port.

### 3. Run the Client

Start the client by providing the server's hostname and port number. Open another terminal and execute:

```bash
java Client <host name> <port number>
```

Example:

```bash
java Client 127.0.0.1 32000
```

### 4. Interaction

- Once the client connects to the server, you will be prompted to input a message (maximum 80 characters).
- Enter your message, and the client will send it to the server.
- The server processes the message and sends the translated message back to the client.
- The client displays the server's response and exits.

## Example Interaction

### Server Terminal

```bash
$ java Server 32000
Server listening on port: 32000
```

### Client Terminal

```bash
$ java Client 127.0.0.1 32000
Client connected to: 127.0.0.1 32000
Enter text: network
Response from server: KROWTEN
```