import socket


sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM) # Create a TCP/IP socket
HOST = "192.168.10.3" # initilize using your own ip address
PORT=8091
sock.bind((HOST,PORT)) #used to assign a local address to a socket and is only used in the server side.
 sock.listen() #prepares the server for incoming clients creating a connection request queue.
  total=2 # Define total no of clients to connect
connections=[]
client_addresses=[]
for _ in range(total):
 print ('waiting for a connection')
 connection, client_address = sock.accept() #when a client is connected with server accept function is called to establish a proper connection.
 connections.append(connection)
 client_addresses.append(client_address)
 print("Connected Client is ",client_address)
 data=[]
 while(len(data)<2):
   data=connection.recv(1024)#1024 specifies no of bytes to receive from the client.
   data=data.decode('utf-8') #used to decode the msg encoded in utf-8 to string.
 data=data[2:] #ignore the first 2 characters because of modified utf-8
 print("Data received from ",client_address," is ",data)
  
for i in range(total):
 data="Received"+'\n' # add a '\n' to tell the message is ended
 a = connections[i].send(data.encode('utf-8')) # send data in encoded format
 print("Response sent to client ",client_addresses[i]) 
