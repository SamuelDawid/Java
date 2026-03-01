package Iterators;

import model.Budget;
import model.User;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class UserListIterator implements Iterator<User> {
    List<User> userList;
    int currentIndex, previousIndex,lastReturnedIndex;
    boolean canRemove;
    Predicate<User> filter;

    public UserListIterator(List<User> userList, Predicate<User> filter) {
        this.userList = userList;
        this.currentIndex = 0;
        this.previousIndex = 0;
        this.lastReturnedIndex = -1;
        this.canRemove = false;
        this.filter = filter;
    }

    @Override
    public boolean hasNext() {
        while (currentIndex < userList.size()){
            if(filter.test(userList.get(currentIndex)))
                return  true;

            currentIndex++;
        }
        return false;
    }

    @Override
    public User next() throws NoSuchElementException {
        if(!hasNext())
            throw new NoSuchElementException();

        User currentuser = userList.get(currentIndex);
        lastReturnedIndex = currentIndex;
        currentIndex++;
        canRemove = true;

        return currentuser;
    }
    public boolean hasPrevious(){
        for(int i = currentIndex;i >= 0;i--){
           if(filter.test(userList.get(i)))
               return true;
        }
        return false;
    }
    public User Previous() throws NoSuchElementException {
            if(!hasPrevious())
                throw new NoSuchElementException();
            User previusUser = userList.get(currentIndex -1);
            currentIndex--;
            lastReturnedIndex = currentIndex;
            canRemove = true;

            return previusUser;
    }
    @Override
    public void remove() throws IllegalStateException{
        if(!canRemove)
            throw new IllegalStateException();

        userList.remove(currentIndex);
        currentIndex--;
        lastReturnedIndex = -1;
        canRemove = false;
    }

    public void set(User user){
        if(lastReturnedIndex == -1)
            throw new IllegalStateException();

        userList.set(lastReturnedIndex,user);
    }
    public void add(User user){
        userList.add(currentIndex,user);
        currentIndex++;
        canRemove = false;
        lastReturnedIndex = -1;
    }
}
