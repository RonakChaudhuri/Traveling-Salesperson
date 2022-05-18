import java.util.*;
import java.awt.Graphics;

/**
 * This class is a specialized Linked List of Points that represents a
 * Tour of locations attempting to solve the Traveling Salesperson Problem
 * 
 * @author
 * @version
 */

public class Tour implements TourInterface
{
    ListNode front;
    ListNode rear;
    int size;

    public Tour()
    {
        front = null;
        rear = null;
    }

    //return the number of points (nodes) in the list   
    public int size()
    {
        return size;
    }

    // append Point p to the end of the list
    public void add(Point p)
    {
        ListNode newNode = new ListNode(p);
        if (rear == null)
            front = newNode;
        else             
        {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    } 

    // print every node in the list 
    public void print()
    {   
        int asize = 0;
        List<Point> p = new ArrayList<Point>();
        while (asize < size)
        {
            Point element;
            element = front.data;
            front = front.next;
            if (front == null)
                rear = null;        
            System.out.println(element);
            p.add(element);
            asize++;
        }
        size = 0;
        rear = null;
        for (int i = 0; i < asize; i++)
        {
            Point x = p.get(i);
            this.add(x);
        }
    }

    // draw the tour using the given graphics context
    public void draw(Graphics g)
    {

        // fillOval(int leftEdge, int topEdge, int width, int height)
        // drawLine(int fromX, int fromY, int toX, int toY)

        int asize = 0;
        List<Point> p = new ArrayList<Point>();
        Point elementFirst = front.data;
        while (asize < size)
        {

            Point element;
            Point elementNext = null;
            element = front.data;
            front = front.next;
            if (p.size() < size-1)
            {
                elementNext = front.data; 
            }
            if (front == null)
                rear = null;     
            if (p.size() < size-1)
            {
                g.drawLine((int)element.getX(), (int)element.getY(), (int)elementNext.getX(), (int)elementNext.getY());
            }
            else 
            {
                g.drawLine((int)element.getX()-2, (int)element.getY(), (int)elementFirst.getX(), (int)elementFirst.getY());
            }

            p.add(element);
            asize++;

        }
        rear = null;
        size = 0;
        for (int i = 0; i < asize; i++)
        {
            Point x = p.get(i);
            this.add(x);
        }
    }

    //calculate the distance of the Tour, but summing up the distance between adjacent points
    //NOTE p.distance(p2) gives the distance where p and p2 are of type Point
    public double distance()
    {

        // fillOval(int leftEdge, int topEdge, int width, int height)
        // drawLine(int fromX, int fromY, int toX, int toY)

        int asize = 0;
        double sum = 0;
        List<Point> p = new ArrayList<Point>();
        Point elementFirst = front.data;
        while (asize < size)
        {

            Point element;
            Point elementNext = null;
            element = front.data;
            front = front.next;
            if (p.size() < size-1)
            {
                elementNext = front.data; 
            }
            if (front == null)
                rear = null;     

            if (p.size() < size-1)
            {
                sum += element.distance(elementNext);
            }
            else 
            {
                sum += element.distance(elementFirst);
            }

            p.add(element);
            asize++;

        }
        size = 0;
        rear = null;
        for (int i = 0; i < asize; i++)
        {
            Point x = p.get(i);
            this.add(x);
        }
        return sum;
    }

    // add Point p to the list according to the NearestNeighbor heuristic
    public void insertNearest(Point p)
    {   

        int asize = 0;
        List<Point> a = new ArrayList<Point>();
        double smallest = Integer.MAX_VALUE;
        int pos = Integer.MAX_VALUE;
        if (size == 0)
        {
            add(p);
        }
        else
        {
            while (asize < size)
            {

                Point element;
                element = front.data;
                front = front.next;
                if (front == null)
                    rear = null;     

                if(element.distance(p)< smallest)
                {
                    smallest = element.distance(p);
                    pos = asize;
                }

                a.add(element);
                asize++;
            }
        }
        if (!(asize == 0))
        {
            size = 0;
            rear = null;
            for (int i = 0; i < asize; i++)
            {
                Point x = a.get(i);
                this.add(x);
            }
            ListNode newNode = new ListNode(p);
            ListNode newRear = positionConvert(pos,front);
            ListNode newRearNext = newRear.next;
            newRear.next = newNode;
            newNode.next = newRearNext;
            rear = positionConvert(asize,front);
            size = asize+1;
        }
    }

    // add Point p to the list according to the InsertSmallest heuristic
    public void insertSmallest(Point p)
    { 
        // double origDist = 0;
        // if (size != 0)
        // {
        // origDist = this.distance();            
        // }
        // System.out.print("origDist: " + origDist);
        int asize = 0;
        List<Point> a = new ArrayList<Point>();
        double smallest = Integer.MAX_VALUE;    
        int pos = 0;
        if (size <= 1)
        {
            add(p);
        }
        else
        {

            while (asize < size)
            {   
                if(front.data.equals(rear.data))
                {
                    a.add(front.data);
                    asize++;
                    break;
                }
                Point element;
                element = front.data;
                front = front.next;
                if (front == null)
                    rear = null;                     
                double newDist = element.distance(p) + front.data.distance(p) -  element.distance(front.data);
                if(newDist < smallest)
                {
                    smallest = newDist;
                    pos = asize;
                }
                a.add(element);
                asize++;
            }
        }
        if (!(asize == 0))
        {
            size = 0;
            rear = null;
           
            for (int i = 0; i < asize; i++)
            {
                Point x = a.get(i);
                this.add(x);
            }
            if((front.data.distance(p) + rear.data.distance(p) -  front.data.distance(rear.data)) < smallest)
                pos = asize-1;
            System.out.print("pos is: " + pos + " | ");
            ListNode newNode = new ListNode(p);
            ListNode newRear = positionConvert(pos,front);
            System.out.print(rear.data);
            System.out.print(newRear.data);
            if (newRear.equals(rear))
            {
                this.add(p);
            }
            else
            {
                ListNode newRearNext = newRear.next;
                newRear.next = newNode;
                newNode.next = newRearNext;
            }
            rear = positionConvert(asize,front);
            size = asize+1;
        }
        if (size == 1)
        {
            System.out.print(asize + " | ");
            System.out.print(size + " | ");
            System.out.println(front.data);
        }
        if (size == 2)
        {
            System.out.print(asize + " | ");
            System.out.print(size + " | ");
            System.out.print(front.data);
            System.out.println(rear.data);
        }
        if (size == 3)
        {
            System.out.print(asize + " | ");
            System.out.print(size + " | ");
            System.out.print(front.data);
            System.out.print(front.next.data);
            System.out.println(rear.data);
        }
        if (size == 4)
        {
            System.out.print(asize + " | ");
            System.out.print(size + " | ");
            System.out.print(front.data);
            System.out.print(front.next.data);
            System.out.print(front.next.next.data);
            System.out.println(rear.data);
        }
        if (size == 5)
        {
            System.out.print(asize + " | ");
            System.out.print(size + " | ");
            System.out.print(front.data);
            System.out.print(front.next.data);
            System.out.print(front.next.next.data);
            System.out.print(front.next.next.next.data);
            System.out.println(rear.data);
        }
    }

    public ListNode positionConvert(int pos,ListNode b)
    {
        if (pos == 0)
            return b;
        else 
        {
            return positionConvert(pos-1,b.next);
        }

    }
    // This is a private inner class, which is a separate class within a class.
    private class ListNode
    {
        private Point data;
        private ListNode next;
        public ListNode(Point p, ListNode n)
        {
            this.data = p;
            this.next = n;
        }

        public ListNode(Point p)
        {
            this(p, null);
        }        
    }
}