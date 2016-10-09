package com.websystique.springmvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.websystique.springmvc.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("requestService")
@Transactional
public class RequestServiceImpl implements RequestService {

    private static final AtomicLong counter = new AtomicLong();

    private static List<Request> requests;

    static{
        requests = populateDummyRequests();
    }

    public List<Request> findAllRequests() {
        return requests;
    }

    public Request findById(long id) {
        for(Request request : requests){
            if(request.getId() == id){
                return request;
            }
        }
        return null;
    }

    public Request findByRequestor(String requestor) {
        for(Request request : requests){
            if(request.getRequestor().equalsIgnoreCase(requestor)){
                return request;
            }
        }
        return null;
    }

    public void saveRequest(Request request) {
        RequestRulesChecker checker = new RequestRulesChecker();
        request.setId(counter.incrementAndGet());
        checker.superRequestCheck(request);
        requests.add(request);
    }

    public void updateRequest(Request request) {
        int index = requests.indexOf(request);
        requests.set(index, request);
    }

    public void deleteRequestById(long id) {

        for (Iterator<Request> iterator = requests.iterator(); iterator.hasNext(); ) {
            Request request = iterator.next();
            if (request.getId() == id) {
                iterator.remove();
            }
        }
    }

    public boolean isRequestExist(Request request) {
        return findByRequestor(request.getRequestor())!=null;
    }

    public void deleteAllRequests(){
        requests.clear();
    }

    private static List<Request> populateDummyRequests(){
        List<Request> requests = new ArrayList<Request>();
        requests.add(new Request(counter.incrementAndGet(),"Sergii", "Create Jenkins unit test job", "sergantty@gmai.com", "Stas", ""));
        requests.add(new Request(counter.incrementAndGet(),"Stas", "Introduce Ansible usage", "kronverk@hotmail.com", "Sergii", ""));
        requests.add(new Request(counter.incrementAndGet(),"Dummy", "Summy task", "dummy_request@gmail.com", "whatever new", ""));
        return requests;
    }

}