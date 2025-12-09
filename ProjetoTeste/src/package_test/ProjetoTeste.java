package package_test;

import javacard.framework.*;

public class ProjetoTeste extends Applet {

    final static byte INS_HELLO = (byte)0x00;

    protected ProjetoTeste() {
        register();
    }

    public static void install(byte[] bArray, short bOffset, byte bLength) {
        new ProjetoTeste();
    }

    public void process(APDU apdu) {
        if (selectingApplet()) return;

        byte[] buffer = apdu.getBuffer();

        if (buffer[ISO7816.OFFSET_INS] == INS_HELLO) {
            byte[] msg = {'O','K'};
            Util.arrayCopyNonAtomic(msg, (short)0, buffer, (short)0, (short)2);
            apdu.setOutgoingAndSend((short)0, (short)2);
            return;
        }

        ISOException.throwIt(ISO7816.SW_INS_NOT_SUPPORTED);
    }
}
