package org.apache.nifi.processors.splitx12;

import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SplitX12Test {
    private TestRunner testRunner;

    @Before
    public void init() {
        testRunner = TestRunners.newTestRunner(new SplitX12());
        testRunner.setValidateExpressionUsage(false);
    }

    @Test
    public void testProcessor() {
        testRunner.assertValid();
    }

    @Test
    public void testOnTriggerOriginal() throws IOException {
        String f = ediFile("/files/double-isa-file-5010.835");

        testRunner.enqueue(Paths.get(f));
        testRunner.run();

        testRunner.assertQueueEmpty();

        List<MockFlowFile> results = testRunner.getFlowFilesForRelationship(SplitX12.REL_ORIGINAL);
        assertEquals("1 match", 1, results.size());

        MockFlowFile result = results.get(0);

        result.assertContentEquals("ISA*00*          *00*          *ZZ*EMEDNYBAZ      *ZZ*ETIN           *100101*1000*^*00501*006000600*0*T*:~GS*HP*EMEDNYBAT*ETIN*20100101*1050*6000600*X*005010X221A1~ST*835*1740~BPR*I*1481.99*C*ACH*CCP*01*111*DA*33*1234567890**01*111*DA*22*20100101~TRN*1*10100000000*1000000000~REF*EV*ETIN~DTM*405*20100101~N1*PR*NYSDOH~N3*OFFICE OF HEALTH INSURANCE PROGRAMS*CORNING TOWER, EMPIRE STATE PLAZA~N4*ALBANY*NY*122370080~PER*BL*PROVIDER SERVICES*TE*8003439000*UR*www.emedny.org~N1*PE*MAJOR MEDICAL PROVIDER*XX*9999999995~LX*1~CLP*PATIENT ACCOUNT NUMBER*3*137.48*0**MC*1000210000000030*34*1~CAS*CO*39*137.48~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL99999L~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*2422~REF*G1*11111111111~DTM*232*20100101~DTM*233*20100101~CLP*PATIENT ACCOUNT NUMBER*1*2286.99*1481.99*805*MC*1000220000000020*21*3~CAS*PR*142*805~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL88888L~NM1*74*1*CORRECTED LAST*CORRECTED FIRST~NM1*82*1******XX*9999999995~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*3839~DTM*232*20100101~DTM*233*20100101~AMT*AU*2286.99~QTY*CA*9~SE*31*1740~GE*1*6000600~IEA*1*006000600~ISA*00*          *00*          *ZZ*EMEDNYBAT      *ZZ*ETIN           *100101*1000*^*00501*006000600*0*T*:~GS*HP*EMEDNYBAT*ETIN*20100101*1050*6000600*X*005010X221A1~ST*835*1740~BPR*I*1481.99*C*ACH*CCP*01*111*DA*33*1234567890**01*111*DA*22*20100101~TRN*1*10100000000*1000000000~REF*EV*ETIN~DTM*405*20100101~N1*PR*NYSDOH~N3*OFFICE OF HEALTH INSURANCE PROGRAMS*CORNING TOWER, EMPIRE STATE PLAZA~N4*ALBANY*NY*122370080~PER*BL*PROVIDER SERVICES*TE*8003439000*UR*www.emedny.org~N1*PE*MAJOR MEDICAL PROVIDER*XX*9999999995~LX*1~CLP*PATIENT ACCOUNT NUMBER*3*137.48*0**MC*1000210000000030*34*1~CAS*CO*39*137.48~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL99999L~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*2422~REF*G1*11111111111~DTM*232*20100101~DTM*233*20100101~CLP*PATIENT ACCOUNT NUMBER*1*2286.99*1481.99*805*MC*1000220000000020*21*3~CAS*PR*142*805~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL88888L~NM1*74*1*CORRECTED LAST*CORRECTED FIRST~NM1*82*1******XX*9999999995~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*3839~DTM*232*20100101~DTM*233*20100101~AMT*AU*2286.99~QTY*CA*9~SE*31*1740~GE*1*6000600~IEA*1*006000600~");
    }

    @Test
    public void testOnTriggerSplit() throws IOException {
        String f = ediFile("/files/double-isa-file-5010.835");

        testRunner.enqueue(Paths.get(f));
        testRunner.run();

        testRunner.assertQueueEmpty();

        List<MockFlowFile> results = testRunner.getFlowFilesForRelationship(SplitX12.REL_SPLIT);
        assertEquals("2 matches", 2, results.size());

        MockFlowFile result0 = results.get(0);
        result0.assertContentEquals("ISA*00*          *00*          *ZZ*EMEDNYBAZ      *ZZ*ETIN           *100101*1000*^*00501*006000600*0*T*:~GS*HP*EMEDNYBAT*ETIN*20100101*1050*6000600*X*005010X221A1~ST*835*1740~BPR*I*1481.99*C*ACH*CCP*01*111*DA*33*1234567890**01*111*DA*22*20100101~TRN*1*10100000000*1000000000~REF*EV*ETIN~DTM*405*20100101~N1*PR*NYSDOH~N3*OFFICE OF HEALTH INSURANCE PROGRAMS*CORNING TOWER, EMPIRE STATE PLAZA~N4*ALBANY*NY*122370080~PER*BL*PROVIDER SERVICES*TE*8003439000*UR*www.emedny.org~N1*PE*MAJOR MEDICAL PROVIDER*XX*9999999995~LX*1~CLP*PATIENT ACCOUNT NUMBER*3*137.48*0**MC*1000210000000030*34*1~CAS*CO*39*137.48~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL99999L~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*2422~REF*G1*11111111111~DTM*232*20100101~DTM*233*20100101~CLP*PATIENT ACCOUNT NUMBER*1*2286.99*1481.99*805*MC*1000220000000020*21*3~CAS*PR*142*805~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL88888L~NM1*74*1*CORRECTED LAST*CORRECTED FIRST~NM1*82*1******XX*9999999995~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*3839~DTM*232*20100101~DTM*233*20100101~AMT*AU*2286.99~QTY*CA*9~SE*31*1740~GE*1*6000600~IEA*1*006000600~");

        MockFlowFile result1 = results.get(1);
        result1.assertContentEquals("ISA*00*          *00*          *ZZ*EMEDNYBAT      *ZZ*ETIN           *100101*1000*^*00501*006000600*0*T*:~GS*HP*EMEDNYBAT*ETIN*20100101*1050*6000600*X*005010X221A1~ST*835*1740~BPR*I*1481.99*C*ACH*CCP*01*111*DA*33*1234567890**01*111*DA*22*20100101~TRN*1*10100000000*1000000000~REF*EV*ETIN~DTM*405*20100101~N1*PR*NYSDOH~N3*OFFICE OF HEALTH INSURANCE PROGRAMS*CORNING TOWER, EMPIRE STATE PLAZA~N4*ALBANY*NY*122370080~PER*BL*PROVIDER SERVICES*TE*8003439000*UR*www.emedny.org~N1*PE*MAJOR MEDICAL PROVIDER*XX*9999999995~LX*1~CLP*PATIENT ACCOUNT NUMBER*3*137.48*0**MC*1000210000000030*34*1~CAS*CO*39*137.48~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL99999L~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*2422~REF*G1*11111111111~DTM*232*20100101~DTM*233*20100101~CLP*PATIENT ACCOUNT NUMBER*1*2286.99*1481.99*805*MC*1000220000000020*21*3~CAS*PR*142*805~NM1*QC*1*SUBMITTED LAST*SUBMITTED FIRST****MI*LL88888L~NM1*74*1*CORRECTED LAST*CORRECTED FIRST~NM1*82*1******XX*9999999995~REF*EA*PATIENT ACCOUNT NUMBER~REF*9A*3839~DTM*232*20100101~DTM*233*20100101~AMT*AU*2286.99~QTY*CA*9~SE*31*1740~GE*1*6000600~IEA*1*006000600~");
    }

    // File too big to check into github
    /*@Test
    public void splitHugeFile() throws IOException {
        Path path = Paths.get("/Users/chrisparker/Code/flow/flowbox/data/nifi-data/original/07042016_07102016.835");
        testRunner.enqueue(path);
        testRunner.run();

        testRunner.assertQueueEmpty();

        List<MockFlowFile> results = testRunner.getFlowFilesForRelationship(SplitX12.REL_SPLIT);
        assertEquals("6462 matches", 6462, results.size());

    }*/

    private String ediFile(String fileName) {
        return this.getClass().getResource(fileName).getPath();
    }
}
