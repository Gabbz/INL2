package funksam;
import java.util.ArrayList;

public class AssignmentDescription {
	
	private ArrayList<AssignmentDescription> descriptionList = new ArrayList<AssignmentDescription>(); 
	String moment1, moment2, moment3, moment4;
	
	public AssignmentDescription (String assignmentCode, String description){
		//FÃ–R ATT SKAPA NY DESC.
	}
	
	public void addDesc(String assignmentCode) {
		
		descriptionList.add(assignmentCode);
		this.moment1 = "Moment 1" + "\nUppgift avseende gruppsykologi i projekt[3 HP] \n\nBeskrivning av projektuppgiften:" +
				"\n1. Inledningsvis skall de olika projektgrupperna vÃ¤lja minst tre omrÃ¥den att fÃ¶rdjupa sig i. "
				+ "\nDessa val av omrÃ¥den utfÃ¶rs vid kursstart, mÃ¥ndag den 5 augusti och skall presenteras pÃ¥ seminarium den 14 augusti."
				+ "\nTeorier avseende de olika fÃ¶rdjupningsomrÃ¥dena finns i kursboken Projektledning av Bo Tonnquist. "
				+ "\n\n2. UtfÃ¶r dÃ¤refter en individuell fÃ¶rdjupning Ã¶ver vad agilt ledarskap kan innebÃ¤ra,"
				+ "\noch skriv ett referat frÃ¥n minst tvÃ¥ bÃ¶cker samt reflektera Ã¶ver hur man kan arbeta agilt i det projektarbete som ni genomfÃ¶r. "
				+ "\n\n3. Referatet och reflektionen dokumenteras pÃ¥ ca fem A4 sidor och skickas till Grupp R, "
				+ "\nvia eâ€�post senast mÃ¥ndag den 23 augusti kl. 17.00. Detta arbete genomfÃ¶rs individuellt av varje student.";
		
		
		this.moment2 ="\n\n\nMoment 2" + "\nUppgift avseende initial kartlÃ¤ggning [2HP] \n\nBeskrivning av projektuppgiften:" +
				"\nUppgiften bestÃ¥r i att utifrÃ¥n en angelÃ¤gen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
				"\nresonera kring affÃ¤rssystem som fenomen och dess â€�mÃ¶teâ€� med organisationer. " +
				"\nInspiration till och stÃ¶d fÃ¶r resonemang kan ocksÃ¥ sÃ¶kas utanfÃ¶r kurslitteraturen, i t.ex. resurslista i Funksam" + 
				"\nunder Kursdokument eller litteratur som sÃ¶ks pÃ¥ egen hand." +
				
				"\n\n1. Var noga med att anvÃ¤nda kÃ¤llor, sÃ¥vÃ¤l tryckta som elektroniska anges tydligt med sedvanlig referenshantering "
				+ "\noch listas (utÃ¶ver de angivna sidorna).Minst fem kÃ¤llor av akademisk karaktÃ¤r." +
				
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nInlÃ¤mning sker senast tisdag 16e september via Funksam.";
		
		this.moment3 = "\n\n\nMoment 3" + "\nUppgift utredningsprojekt[6 HP] \n\nBeskrivning av projektuppgiften:" +
				"\nSyftet med projektarbetet Ã¤r att skaffa sig kunskap om olika perspektiv pÃ¥ affÃ¤rssystem och organisering. "
				+ "Arbetet skall i fÃ¶rsta hand bedrivas i grupper om fyra studenter. "
				+ "Projektuppgiften kan Ã¶vergripande beskrivas som mÃ¶jlig att till stor del anpassa till projektgruppens intresse "
				+ "med avseende pÃ¥ sitt innehÃ¥ll inom kursens ramar."
				+ "Uppgiften bestÃ¥r i att utifrÃ¥n en angelÃ¤gen och aktuell debatt samt ur kurslitteraturen/referenslitteraturen i en PM,  " +
		
		
				"\n\n1. Var noga med att anvÃ¤nda kÃ¤llor, sÃ¥vÃ¤l tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (utÃ¶ver de angivna sidorna).Minst tio kÃ¤llor av akademisk karaktÃ¤r." +
				
				"\n\n2. Uppgiften redovisas i en PM omfattande maximalt sex A4-sidor (cirka 4000 ord). +"
				+ "\nProjektarbetet lÃ¤mnas in vid tre tillfÃ¤llen under kursens gÃ¥ng enligt schema. "
				+ "\nTvÃ¥ gÃ¥nger infÃ¶r handledning samt en slutinlÃ¤mning. Samtliga inlÃ¤mningar sker via FUNKSAM.";
		
		this.moment4 = "\n\n\n\nMoment 4" + "\nUppgift avseende Design och vÃ¤rdering av en e-tjÃ¤nst [4HP] \n\nBeskrivning av projektuppgiften:"		 

				+ "\nUppgiften innebÃ¤r att design och utvÃ¤rdering skall ske kriteriebaserat utifrÃ¥n av gruppen formulerade kriterier. "
				+ "\nE-tjÃ¤nsten skall visualiseras pÃ¥ det sÃ¤tt gruppen vÃ¤ljer; t.ex. genom skisser Ã¶ver tjÃ¤nsten, grÃ¤nssnitt, grafisk prototyp eller liknande. "
				
				+ "\n\n1.Var noga med att anvÃ¤nda kÃ¤llor, sÃ¥vÃ¤l tryckta som elektroniska anges tydligt med sedvanlig referenshantering " 
				+ "\noch listas (utÃ¶ver de angivna sidorna).Minst tio kÃ¤llor av akademisk karaktÃ¤r." +
		
				"\n\n2. Projektarbetet lÃ¤mnas in vid tre tillfällen under kursens gång enligt schema. \nTvå gånger inför handledning samt en slutinlämning. "
				+ "Samtliga inlämningar sker senast fredagen den 15 september kl. 08.00.";
		
	
		System.out.println(moment1 + "\\n" + "\\n" + moment2 + "\\n" + moment3 + "\\n" + moment4);
		
		//public void getDescription(){
		
		//}

	}
}