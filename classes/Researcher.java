package classes;
import exceptions.InvalidSupervisorException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Researcher extends Employee {
	private List<ResearchPaper> researchPapers;
	private List<ResearchProject> researchProjects;
	
	public Researcher(String id, String userName, String password) {
		super(id, userName, password, password);
	}
	
    public void addResearchPaper(ResearchPaper paper) {
        researchPapers.add(paper);
    }

    public void printPapers(Comparator<ResearchPaper> comparator) {
    }

    public int calculateHIndex() {
        int hIndex = 0;

        return hIndex;
    }
    
    public String toString() {
    	return "";
    }
}