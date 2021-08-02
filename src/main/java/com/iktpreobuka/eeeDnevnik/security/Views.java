package com.iktpreobuka.eeeDnevnik.security;

public class Views {
	public static class ucenik {}
	public static class roditelj extends ucenik {}
	public static class nastavnik extends roditelj {}
	public static class admin extends nastavnik {}

}
