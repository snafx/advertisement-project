package ogloszenia;

import org.hibernate.Session;

import ogloszenia.model.Advertisement;
import ogloszeniar.hibernate.util.HibernateUtil;

public class Test {

	public static void main(String[] args) {
		Session s = HibernateUtil.openSession().getSession();
		
		Advertisement o  = s.find(Advertisement.class, 1);

	}

}
