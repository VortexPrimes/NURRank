package me.vortexprimes.nur.extras;

import me.vortexprimes.nur.Ranks;

public class convertToTier {
	
	
	
	public static Ranks toTier(int i) {
		if(i == 1) {
			return Ranks.Dminus;
		}
		if(i == 2) {
			return Ranks.D;
		}
		if(i == 3) {
			return Ranks.Dplus;
		}
		if(i == 4) {
			return Ranks.Cminus;
		}
		if(i == 5) {
			return Ranks.C;
		}
		if(i == 6) {
			return Ranks.Cplus;
		}
		if(i == 7) {
			return Ranks.Bminus;
		}
		if(i == 8) {
			return Ranks.B;
		}
		if(i == 9) {
			return Ranks.Bplus;
		}
		if(i == 10) {
			return Ranks.Aminus;
		}
		if(i == 11) {
			return Ranks.A;
		}
		if(i == 12) {
			return Ranks.Aplus;
		}
		if(i == 13) {
			return Ranks.Sminus;
		}
		if(i == 14) {
			return Ranks.S;
		}
		if(i >= 15) {
			return Ranks.Splus;
		}
		return Ranks.Dminus;
		
	}

}
