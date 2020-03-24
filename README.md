# EhCache Replication Example

### Build

[Explore the Project's Repository](https://github.com/skrymets/index.html)

Clone the Repository
```bash
git clone https://github.com/skrymets/replicated-cache.git 
```

Build the Project
```bash
cd replicated-cache
mvn clean install -DskipTests=true
```

Build Docker Image
```bash
docker image build . -t cache-demo:1.0.0
```

### Play

Run Application's instances in two nodes by Docker Composer
```bash
docker-compose.exe up
```
You will see an output like this
> Creating replicated-cache_node1_1 ... done  
> Creating replicated-cache_node2_1 ... done  
> Attaching to replicated-cache_node1_1, replicated-cache_node2_1

* **Node 1** REST Service will start on port **8080**
* **Node 2** REST Service will start on port **8081**

Use __curl__ to read/write values from/in cache via the REST interface.

Write a value to the **Node 1**
```bach
curl -X POST -H "key:keyValue" -H "value:valueData" http://localhost:8080/set
```
> node1_1  | 2020-03-24 22:21:18.277  INFO 1 --- [nio-8080-exec-2] s.replicated.cache.shell.RestShell       : Setup value for key keyValue == valueData  
> node1_1  | 2020-03-24 22:21:18.280  INFO 1 --- [nio-8080-exec-2] s.replicated.cache.CachingRepository     : Update value in the cache: keyValue: valueData  
> node2_1  | 2020-03-24 22:21:19.116 DEBUG 1 --- [n(5)-172.17.0.2] n.sf.ehcache.distribution.RMICachePeer   : RMICachePeer for cache inMemoryFeatures: remote remove received for key: feature-keyValue 

Now read a value by the provided key from the **Node 1**
```bash
curl -X GET http://localhost:8080/read?key=keyValue
```

You'll get:
> valueData  

Now read a value by the provided key from the **Node 2**
```bash
curl -X GET http://localhost:8081/read?key=keyValue
```

And ... magically ... you'll get the same value:
> valueData  

Explore the logs output in console for more details.

Shutdown the nodes
```bash
docker-compose.exe down
```

### Setup Info

[EhCache RMI Setup](https://www.ehcache.org/documentation/2.7/replication/rmi-replicated-caching.html)