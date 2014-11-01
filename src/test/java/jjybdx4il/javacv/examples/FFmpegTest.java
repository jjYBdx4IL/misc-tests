package jjybdx4il.javacv.examples;

import java.awt.image.BufferedImage;
import java.io.File;
import org.bytedeco.javacpp.avcodec;
import org.bytedeco.javacpp.avutil;
import org.bytedeco.javacpp.opencv_core.IplImage;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.FrameRecorder;
import org.junit.Ignore;
import org.junit.Test;

public class FFmpegTest extends Base {

    public static final int w = 640;
    public static final int h = 480;

    /**
     * Bug preventing this: https://github.com/bytedeco/javacpp-presets/issues/22
     *
     * @throws org.bytedeco.javacv.FrameRecorder.Exception
     */
    @Ignore
    @Test
    public void test() throws FrameRecorder.Exception {
        File outFile = getTempFile(FFmpegTest.class.getName() + ".avi");
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outFile, w, h);

        recorder.setVideoCodec(13);
        recorder.setVideoCodec(avcodec.AV_CODEC_ID_H264);
        recorder.setFormat("mp4");
        recorder.setPixelFormat(avutil.PIX_FMT_YUV420P9);
        recorder.setFrameRate(30);
        recorder.setVideoBitrate(10 * 1024 * 1024);

        recorder.start();
        BufferedImage img = new BufferedImage(BufferedImage.TYPE_3BYTE_BGR, w, h);
        IplImage ipl = IplImage.createFrom(img);
        recorder.record(ipl);
        recorder.stop();
    }
}
