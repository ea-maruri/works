class LectorFicheroSD{
  private:
    File fileToRead;
  public:
    LectorFicheroSD(File pFileToRead) : fileToRead(pFileToRead){} //Constructor
    String readALine();
};
