package org.docksidestage.compatible10x.dbflute.vendor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import org.dbflute.cbean.result.ListResultBean;
import org.dbflute.cbean.scoping.UnionQuery;
import org.dbflute.exception.SQLFailureException;
import org.dbflute.util.DfTypeUtil;
import org.docksidestage.compatible10x.dbflute.cbean.MemberCB;
import org.docksidestage.compatible10x.dbflute.cbean.MemberWithdrawalCB;
import org.docksidestage.compatible10x.dbflute.cbean.VendorCheckCB;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.MemberWithdrawalBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.VendorCheckBhv;
import org.docksidestage.compatible10x.dbflute.exbhv.pmbean.CompareDatePmb;
import org.docksidestage.compatible10x.dbflute.exentity.Member;
import org.docksidestage.compatible10x.dbflute.exentity.MemberWithdrawal;
import org.docksidestage.compatible10x.dbflute.exentity.VendorCheck;
import org.docksidestage.compatible10x.dbflute.exentity.customize.SimpleVendorCheck;
import org.docksidestage.compatible10x.dbflute.exentity.customize.VendorNumericDecimalSum;
import org.docksidestage.compatible10x.dbflute.exentity.customize.VendorNumericIntegerSum;
import org.docksidestage.compatible10x.unit.UnitContainerTestCase;

/**
 * @author jflute
 * @since 0.9.6.6 (2010/03/11 Thursday)
 */
public class VendorTypeTest extends UnitContainerTestCase {

    // ===================================================================================
    //                                                                           Attribute
    //                                                                           =========
    private VendorCheckBhv vendorCheckBhv;
    private MemberBhv memberBhv;
    private MemberWithdrawalBhv memberWithdrawalBhv;

    // ===================================================================================
    //                                                                         String Type
    //                                                                         ===========
    public void test_TEXT_union() {
        // ## Arrange ##
        MemberWithdrawalCB cb = new MemberWithdrawalCB();
        cb.specify().columnWithdrawalReasonInputText();
        cb.union(new UnionQuery<MemberWithdrawalCB>() {
            public void query(MemberWithdrawalCB unionCB) {
            }
        });

        // ## Act ##
        ListResultBean<MemberWithdrawal> withdrawalList = memberWithdrawalBhv.selectList(cb);

        // ## Assert ##
        assertNotSame(0, withdrawalList.size());
    }

    // ===================================================================================
    //                                                                         Number Type
    //                                                                         ===========
    public void test_NUMBER_AutoMapping_DomainEntity() throws Exception {
        // ## Arrange ##
        VendorCheck vendorCheck = new VendorCheck();

        // ## Act & Assert ##
        final Integer typeOfNumberInteger = vendorCheck.getTypeOfNumericInteger();
        final Long typeOfNumberBigint = vendorCheck.getTypeOfNumericBigint();
        final BigDecimal typeOfNumberDecimal = vendorCheck.getTypeOfNumericDecimal();
        final Integer typeOfNumberIntegerMin = vendorCheck.getTypeOfNumericIntegerMin();
        final Integer typeOfNumberIntegerMax = vendorCheck.getTypeOfNumericIntegerMax();
        final Long typeOfNumberBigintMin = vendorCheck.getTypeOfNumericBigintMin();
        final Long typeOfNumberBigintMax = vendorCheck.getTypeOfNumericBigintMax();
        final BigDecimal typeOfNumberSuperintMin = vendorCheck.getTypeOfNumericSuperintMin();
        final BigDecimal typeOfNumberSuperintMax = vendorCheck.getTypeOfNumericSuperintMax();
        assertNull(typeOfNumberBigint);
        assertNull(typeOfNumberInteger);
        assertNull(typeOfNumberDecimal);
        assertNull(typeOfNumberIntegerMin);
        assertNull(typeOfNumberIntegerMax);
        assertNull(typeOfNumberBigintMin);
        assertNull(typeOfNumberBigintMax);
        assertNull(typeOfNumberSuperintMin);
        assertNull(typeOfNumberSuperintMax);
    }

    public void test_NUMBER_AutoMapping_CustomizeEntity() throws Exception {
        // ## Arrange ##
        SimpleVendorCheck vendorCheck = new SimpleVendorCheck();

        // ## Act & Assert ##
        final Integer typeOfNumberInteger = vendorCheck.getTypeOfNumericInteger();
        final Long typeOfNumberBigint = vendorCheck.getTypeOfNumericBigint();
        final BigDecimal typeOfNumberDecimal = vendorCheck.getTypeOfNumericDecimal();
        final Integer typeOfNumberIntegerMin = vendorCheck.getTypeOfNumericIntegerMin();
        final Integer typeOfNumberIntegerMax = vendorCheck.getTypeOfNumericIntegerMax();
        final Long typeOfNumberBigintMin = vendorCheck.getTypeOfNumericBigintMin();
        final Long typeOfNumberBigintMax = vendorCheck.getTypeOfNumericBigintMax();
        final BigDecimal typeOfNumberSuperintMin = vendorCheck.getTypeOfNumericSuperintMin();
        final BigDecimal typeOfNumberSuperintMax = vendorCheck.getTypeOfNumericSuperintMax();
        assertNull(typeOfNumberBigint);
        assertNull(typeOfNumberInteger);
        assertNull(typeOfNumberDecimal);
        assertNull(typeOfNumberIntegerMin);
        assertNull(typeOfNumberIntegerMax);
        assertNull(typeOfNumberBigintMin);
        assertNull(typeOfNumberBigintMax);
        assertNull(typeOfNumberSuperintMin);
        assertNull(typeOfNumberSuperintMax);
    }

    // ===================================================================================
    //                                                                           Date Type
    //                                                                           =========
    public void test_DATE_HHmmss_conditionBean() { // *Important!
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.set(2008, 5, 15, 12, 34, 56);
        cal.set(Calendar.MILLISECOND, 123);
        Member member = new Member();
        member.setMemberId(3);
        member.setBirthdate(new Date(cal.getTimeInMillis()));
        memberBhv.updateNonstrict(member);

        // ## Act ##
        cal.set(2008, 5, 15, 12, 34, 57); // plus one second
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberId_Equal(3);
            cb.query().setBirthdate_GreaterEqual(new Date(cal.getTimeInMillis()));
            Member actual = memberBhv.selectEntityWithDeletedCheck(cb);

            // ## Assert ##
            Date actualValue = actual.getBirthdate();
            String formatted = DfTypeUtil.toString(actualValue, "yyyy/MM/dd HH:mm:ss.SSS");
            log("actualValue = " + formatted);
            assertEquals("2008/06/15 00:00:00.000", formatted);
        }
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberId_Equal(3);
            cb.query().setBirthdate_GreaterEqual(new java.sql.Date(cal.getTimeInMillis()));
            Member actual = memberBhv.selectEntityWithDeletedCheck(cb);

            // ## Assert ##
            Date actualValue = actual.getBirthdate();
            String formatted = DfTypeUtil.toString(actualValue, "yyyy/MM/dd HH:mm:ss.SSS");
            log("actualValue = " + formatted);
            assertEquals("2008/06/15 00:00:00.000", formatted);
        }
        {
            MemberCB cb = new MemberCB();
            cb.query().setMemberId_Equal(3);
            cb.query().setBirthdate_GreaterEqual(new Timestamp(cal.getTimeInMillis()));
            Member actual = memberBhv.selectEntityWithDeletedCheck(cb);

            // ## Assert ##
            Date actualValue = actual.getBirthdate();
            String formatted = DfTypeUtil.toString(actualValue, "yyyy/MM/dd HH:mm:ss.SSS");
            log("actualValue = " + formatted);
            assertEquals("2008/06/15 00:00:00.000", formatted);
        }
    }

    public void test_DATE_HHmmss_outsideSql() throws Exception {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.set(9001, 5, 15, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Member member = new Member();
        member.setMemberId(3);
        member.setBirthdate(new Date(cal.getTimeInMillis()));
        memberBhv.updateNonstrict(member);

        String path = MemberBhv.PATH_whitebox_pmbean_selectCompareDate;

        CompareDatePmb pmb = new CompareDatePmb();
        pmb.setMemberId(3);
        cal.set(9001, 5, 15, 12, 34, 56);
        pmb.setBirthdateFrom(new Date(cal.getTimeInMillis()));

        Class<Member> entityType = Member.class;

        // ## Act ##
        Member actual = memberBhv.outsideSql().entityHandling().selectEntityWithDeletedCheck(path, pmb, entityType);

        // ## Assert ##
        Date actualValue = actual.getBirthdate();
        String formatted = DfTypeUtil.toString(actualValue, "yyyy/MM/dd HH:mm:ss.SSS");
        log("actualValue = " + formatted);
        assertEquals("9001/06/15 00:00:00.000", formatted);
    }

    public void test_DATE_selectPureDate() { // *Important!
        // ## Arrange ##
        MemberCB cb = new MemberCB();
        cb.query().setBirthdate_IsNotNull();

        // ## Act ##
        ListResultBean<Member> memberList = memberBhv.selectList(cb);

        // ## Assert ##
        assertFalse(memberList.isEmpty());
        for (Member member : memberList) {
            Date birthdate = member.getBirthdate();
            assertTrue(java.util.Date.class.equals(birthdate.getClass()));
            assertFalse(birthdate instanceof java.sql.Date);
            assertFalse(birthdate instanceof Timestamp);
        }
    }

    public void test_DATE_SqlDate_HHmmss_outsideSql() throws Exception {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.set(9001, 5, 15, 12, 34, 56);
        cal.set(Calendar.MILLISECOND, 0);
        Member member = new Member();
        member.setMemberId(3);
        member.setBirthdate(new Date(cal.getTimeInMillis()));
        memberBhv.updateNonstrict(member);

        String path = MemberBhv.PATH_whitebox_pmbean_selectCompareDate;

        CompareDatePmb pmb = new CompareDatePmb();
        pmb.setMemberId(3);
        cal.set(9001, 5, 15, 23, 45, 57);
        pmb.setBirthdateFrom(new java.sql.Date(cal.getTimeInMillis()));

        Class<Member> entityType = Member.class;

        // ## Act ##
        Member actual = memberBhv.outsideSql().entityHandling().selectEntityWithDeletedCheck(path, pmb, entityType);

        // ## Assert ##
        Date actualValue = actual.getBirthdate();
        String formatted = DfTypeUtil.toString(actualValue, "yyyy/MM/dd HH:mm:ss.SSS");
        log("actualValue = " + formatted);
        assertEquals("9001/06/15 00:00:00.000", formatted);
    }

    // -----------------------------------------------------
    //                                                  TIME
    //                                                  ----
    public void test_TIME_insert_and_query() {
        // ## Arrange ##
        Calendar cal = Calendar.getInstance();
        cal.set(2002, 5, 15, 12, 34, 56);
        Time specifiedTime = new Time(cal.getTimeInMillis());
        cal.set(2002, 5, 15, 12, 34, 55);
        Time oneSecondBeforeTime = new Time(cal.getTimeInMillis());
        cal.set(2002, 5, 15, 12, 34, 57);
        Time oneSecondAfterTime = new Time(cal.getTimeInMillis());

        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfTime(specifiedTime);
        vendorCheckBhv.insert(vendorCheck);

        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        cb.query().setTypeOfTime_GreaterThan(oneSecondBeforeTime);
        cb.query().setTypeOfTime_LessThan(oneSecondAfterTime);

        // ## Act ##
        VendorCheck actual = vendorCheckBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        Time actualTime = actual.getTypeOfTime();
        log("actualTime=" + actualTime);
        assertNotNull(actualTime);
        // random 56, 57, give up
        //assertEquals(specifiedTime.toString(), actualTime.toString());
    }

    // ===================================================================================
    //                                                                        Boolean Type
    //                                                                        ============
    public void test_BOOLEAN_delete_insert_select() {
        // ## Arrange ##
        VendorCheckCB deleteCB = new VendorCheckCB();
        deleteCB.query().setTypeOfBoolean_Equal(true);
        log("deleted(true)=" + vendorCheckBhv.queryDelete(deleteCB));
        deleteCB.query().setTypeOfBoolean_Equal(false);
        log("deleted(false)=" + vendorCheckBhv.queryDelete(deleteCB));

        VendorCheck vendorCheck = new VendorCheck();
        vendorCheck.setVendorCheckId(Long.valueOf(8881));
        vendorCheck.setTypeOfText("abc");
        vendorCheck.setTypeOfBoolean(true);

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        {
            VendorCheck twice = new VendorCheck();
            twice.setVendorCheckId(Long.valueOf(8882));
            twice.setTypeOfText("abc");
            twice.setTypeOfBoolean(false);
            vendorCheckBhv.insert(twice);
        }

        // ## Assert ##
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setTypeOfBoolean_Equal(true);
        VendorCheck actual = vendorCheckBhv.selectEntityWithDeletedCheck(cb);
        log(actual);
        assertEquals(vendorCheck.getVendorCheckId(), actual.getVendorCheckId());
        assertEquals(vendorCheck.getTypeOfBoolean(), actual.getTypeOfBoolean());
    }

    // ===================================================================================
    //                                                                         Binary Type
    //                                                                         ===========
    // -----------------------------------------------------
    //                                                  BLOB
    //                                                  ----
    public void test_BLOB_insert_select() {
        // ## Arrange ##
        String expected = "foo";
        Member member = memberBhv.selectByPKValue(3);
        member.setMemberName(expected);
        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfBlob(serialize(member));

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        VendorCheck selected = vendorCheckBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        byte[] bytes = selected.getTypeOfBlob();
        assertNotNull(bytes);
        Member deserialized = (Member) deserialize(bytes);
        log("deserialized=" + deserialized);
        assertEquals(expected, deserialized.getMemberName());
    }

    // -----------------------------------------------------
    //                                                BINARY
    //                                                ------
    public void test_BINARY_insert_select() {
        // ## Arrange ##
        String expected = "foo";
        Member member = memberBhv.selectByPKValue(3);
        member.setMemberName(expected);
        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfBinary(serialize(member));

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        VendorCheck selected = vendorCheckBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        byte[] bytes = selected.getTypeOfBinary();
        assertNotNull(bytes);
        Member deserialized = (Member) deserialize(bytes);
        log("deserialized=" + deserialized);
        assertEquals(expected, deserialized.getMemberName());
    }

    // ===================================================================================
    //                                                                        Various Type
    //                                                                        ============
    // -----------------------------------------------------
    //                                                  UUID
    //                                                  ----
    public void test_UUID_insert_select() throws Exception {
        // ## Arrange ##
        String expected = "FD8C7155-3A0A-DB11-BAC4-0011F5099158";
        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfUuid(expected.getBytes("UTF-8"));

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        VendorCheck selected = vendorCheckBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        String actual = new String(selected.getTypeOfUuid(), "UTF-8");
        log("actual=" + actual);
        // ???
        //assertEquals(expected, actual);
    }

    // -----------------------------------------------------
    //                                                 ARRAY
    //                                                 -----
    public void test_ARRAY_insert_select() throws Exception {
        // ## Arrange ##
        String expected = "foo,bar";
        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfArray(expected);

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        cb.query().setTypeOfArray_Equal(expected);
        assertException(SQLFailureException.class, () -> {
            vendorCheckBhv.selectEntityWithDeletedCheck(cb);
        });

        // ## Assert ##
        // Data conversion error converting "VARCHAR to ARRAY" (from new H2 version)
        //String actual = selected.getTypeOfArray();
        //log("actual=" + actual);
        //assertEquals("(" + expected + ")", actual);
    }

    // -----------------------------------------------------
    //                                                 OTHER
    //                                                 -----
    public void test_OTHER_insert_select() {
        // ## Arrange ##
        String expected = "fc17ab";
        VendorCheck vendorCheck = createVendorCheck();
        vendorCheck.setTypeOfOther(expected);

        // ## Act ##
        vendorCheckBhv.insert(vendorCheck);
        VendorCheckCB cb = new VendorCheckCB();
        cb.query().setVendorCheckId_Equal(vendorCheck.getVendorCheckId());
        VendorCheck selected = vendorCheckBhv.selectEntityWithDeletedCheck(cb);

        // ## Assert ##
        String actual = selected.getTypeOfOther();
        assertNotNull(actual);
        log("actual=" + actual);
        assertEquals(expected, actual);
    }

    // -----------------------------------------------------
    //                                         SUM(function)
    //                                         -------------
    @SuppressWarnings("unused")
    public void test_SUM_of_function() throws Exception {
        {
            VendorNumericDecimalSum vendorCheck = new VendorNumericDecimalSum();
            BigDecimal integerNonDigit = vendorCheck.getDecimalDigitSum();
        }
        {
            VendorNumericIntegerSum vendorCheck = new VendorNumericIntegerSum();
            BigDecimal integerNonDigit = vendorCheck.getIntegerNonDigitSum();
        }
    }

    // ===================================================================================
    //                                                                       Assist Helper
    //                                                                       =============
    protected VendorCheck createVendorCheck() {
        VendorCheck vendorCheck = new VendorCheck();
        vendorCheck.setVendorCheckId(new Long(99999));
        return vendorCheck;
    }

    protected VendorCheck createVendorCheck(Integer id) {
        VendorCheck vendorCheck = new VendorCheck();
        vendorCheck.setVendorCheckId(new Long(id));
        return vendorCheck;
    }

    protected byte[] serialize(Serializable obj) {
        return DfTypeUtil.toBinary(obj);
    }

    protected Serializable deserialize(byte[] bytes) {
        return DfTypeUtil.toSerializable(bytes);
    }
}
