package com.test.viewpagerfun;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.test.viewpagerfun.model.datasource.NoteRepository;
import com.test.viewpagerfun.model.entity.Note;

import java.util.ArrayList;
import java.util.List;

public class SharedViewModel extends AndroidViewModel {

    private NoteRepository repository;

    private final LiveData<List<Note>> notes;
    private MutableLiveData<Integer>  position = new MutableLiveData<>(0);
    private List<Note> remainingNotes;

    //constructor for loading from database
    public SharedViewModel(Application application){
        super(application);
        repository = new NoteRepository(application);
        notes = repository.getAllNotes();
        remainingNotes = new ArrayList<>();
    }

    //constructor for storing remaining notes from a previous review
    public SharedViewModel(Application application, List<Note> previousNotes){
        super(application);
        notes = new MutableLiveData<>(previousNotes);
        remainingNotes = new ArrayList<>();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    // Position: Getter and Setter
    public MutableLiveData<Integer> getPosition(){ return position; }
    public void setPosition(int x){ position.setValue(x);}

    public LiveData<Note> getNote() {
        Note noteAtCurrentPosition = notes.getValue().get(position.getValue());
        return new MutableLiveData<>(noteAtCurrentPosition);
    }

    public Note getNoteAtPosition(int position) {
        return notes.getValue().get(position);
    }

    public boolean hasNextNote() {
        Integer currentPosition = position.getValue();
        Integer lastItemIndex = notes.getValue().size()-1;

        if (currentPosition < lastItemIndex){
            // Increment our position. Makes it possible to load next note when called before getNote()
            getPosition().setValue(++currentPosition);
            return true;
        }else{
            return false;
        }
    }

    //get those notes which have not been reviewed by the user
    public List<Note> getRemainingNotes(){
        if(remainingNotes.size() == 0){
            for (int i=position.getValue(); i<notes.getValue().size(); i++){
                remainingNotes.add(getNoteAtPosition(i));
            }
        }
        return remainingNotes;

    }
}


