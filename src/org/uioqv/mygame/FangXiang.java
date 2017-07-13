package org.uioqv.mygame;

public enum FangXiang {
	SHANG, XIA, ZUO, YOU;
	public static boolean isInvalid(FangXiang f1, FangXiang f2){
		if(f1 == SHANG || f1 == XIA){
			if(f2 == SHANG || f2 == XIA)
				return true;
		}
		if(f1 == ZUO || f1 == YOU){
			if(f2 == ZUO || f2 == YOU)
				return true;
		}
		return false;
	}
	public static FangXiang contraryFangXiang(FangXiang fx) {
		switch(fx){
		case SHANG: return XIA;
		case XIA: return SHANG;
		case ZUO: return YOU;
		case YOU: return ZUO;
		}
		throw new RuntimeException("反方向判断故障");
	}
}
