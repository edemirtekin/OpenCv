import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class Opencv {
    public static void main(String[] args) {


        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Yüz tanıma işlemi için gerekli olan OpenCV sınıfı yüklenir
        CascadeClassifier faceDetector = new CascadeClassifier("haarcascade_frontalface_default.xml");

        // Tanımlanacak görüntüyü yükleme ve gri tonlamaya çevirme işlemi
        Mat image = Imgcodecs.imread("xxx.jpg", Imgcodecs.IMREAD_GRAYSCALE);

        // Görüntü üzerinde yüz tanıma işlemi gerçekleştirilir
        MatOfRect faceDetections = new MatOfRect();
        //33 değeri doğruluk değeridir değer düşerse hatalı sonuç artar yükselirse hatasız sonuç artar
        faceDetector.detectMultiScale(image, faceDetections, 1.05, 33, 0, new Size(30, 30), new Size());

        // Tanınan yüzlerin sayısı
        //System.out.println(String.format("Yüzler bulundu: %s", faceDetections.toArray().length));

        System.out.println(faceDetections.toArray().length);

        // Tanınan yüzler için döngü oluşturulur
        for (Rect rect : faceDetections.toArray()) {
            // Yüzler için kare çizdirilir
            Imgproc.rectangle(image, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255), 2);
        }

        // Tanımlanmış yüzler için güncellenmiş görüntü kaydedilir
        String filename = "output.jpg";
        Imgcodecs.imwrite(filename, image);
        System.out.println("Yeni resim yazdırıldı");

        System.exit(0);

    }
}
