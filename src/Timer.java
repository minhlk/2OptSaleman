class Timer {
    double startTime;
    Timer(){
        this.startTime = System.currentTimeMillis();
    }
    double getTimeTaken(){
        return System.currentTimeMillis() - startTime;
    }

}