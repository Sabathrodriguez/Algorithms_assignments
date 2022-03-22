public class Main{
    public static void main(String[] args) {
        double[][] bottles = {
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                  {1.1,1.1,1.1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1},
                        {1,1,1}};

        int positionOfHeavyBottle = -1;
        double totWeight = 0;
        for (int i = 0; i < bottles.length; i++)
        {
            totWeight += bottleWeight(bottles[i]);
            if (totWeight % 1 != 0)
            {
                positionOfHeavyBottle = i;
                break;
            }
        }
        
        System.out.println(positionOfHeavyBottle);
    }
    private static double bottleWeight(double[] bottle)
    {
        double sm = 0;
        for (int i = 0; i < bottle.length; i++)
        {
            sm += bottle[i];
        }
        return sm;
    }
    
}
