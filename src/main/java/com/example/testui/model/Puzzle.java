package com.example.testui.model;
import java.io.Serializable;

public class Puzzle implements Serializable {
	private static final long serialVersionUID = 1234L;
	private int initRoomID;
	private int puzzleID;
	private String puzzleName;
	private int attempts;
	private String question;
	private String answer;
	private String solvedMessage;
	private String hint;
	private int puzzleDmg;
	
	//Constructor
	public Puzzle(int initRoomID, int puzzleID, String puzzleName, int attempts, String question, String answer,
			String solvedMessage, String hint, int puzzleDmg) {
		super();
		this.initRoomID = initRoomID;
		this.puzzleID = puzzleID;
		this.puzzleName = puzzleName;
		this.attempts = attempts;
		this.question = question;
		this.answer = answer;
		this.solvedMessage = solvedMessage;
		this.hint = hint;
		this.puzzleDmg = puzzleDmg;
	}
	
	//Getter
	public int getInitRoomID() { return initRoomID; }
	
	public int getPuzzleID() { return puzzleID; }
	
	public String getPuzzleName() { return puzzleName; }
	
	public int getAttempts() { return attempts; }
	
	public String getQuestion() { return question; }
	
	public String getAnswer() { return answer; }
	
	public String getSolvedMessage() { return solvedMessage; }
	
	public String getHint() { return hint; }
	
	public int getPuzzleDmg() { return puzzleDmg; }

	@Override
	public String toString() {
		return "Puzzle [initRoomID=" + initRoomID + ", puzzleID=" + puzzleID + ", puzzleName=" + puzzleName
				+ ", attempts=" + attempts + ", question=" + question + ", answer=" + answer + ", solvedMessage="
				+ solvedMessage + ", hint=" + hint + ", puzzleDmg=" + puzzleDmg + "]\n";
	}
	
	
	
}
