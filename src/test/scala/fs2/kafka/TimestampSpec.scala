package fs2.kafka

final class TimestampSpec extends BaseSpec {
  describe("Timeout#createTime") {
    it("should have a createTime") {
      forAll { value: Long =>
        Timestamp.createTime(value).createTime shouldBe Some(value)
      }
    }

    it("should not have a logAppendTime") {
      forAll { value: Long =>
        Timestamp.createTime(value).logAppendTime shouldBe None
      }
    }

    it("should include the createTime in toString") {
      forAll { value: Long =>
        Timestamp.createTime(value).toString should include(s"createTime = $value")
      }
    }
  }

  describe("Timeout#logAppendTime") {
    it("should have a logAppendTime") {
      forAll { value: Long =>
        Timestamp.logAppendTime(value).logAppendTime shouldBe Some(value)
      }
    }

    it("should not have a createTime") {
      forAll { value: Long =>
        Timestamp.logAppendTime(value).createTime shouldBe None
      }
    }

    it("should include the logAppendTime in toString") {
      forAll { value: Long =>
        Timestamp.logAppendTime(value).toString should include(s"logAppendTime = $value")
      }
    }
  }
}
