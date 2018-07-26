/**
 * @file index.vue
 * @author liumapp
 * @email liumapp.com@gmail.com
 * @homepage http://www.liumapp.com
 * @date 7/24/18
 */
<template>
  <div>
    <Row>
      <Col span="18" offset="3">
      <Card>
        <Steps :current="current">
          <Step title="上传匹对图片" content="上传一张自拍照和一张身份证正面照片，匹对两张图片的人脸是否为同一个人" @next="nextStep"></Step>
          <Step title="配对结果" content="图片相似程度，包括清晰度等参数"></Step>
        </Steps>
        <pic-upload v-if="current == 0"></pic-upload>
        <report v-else-if="current == 1"></report>
        <div v-else>
          wrong current value
        </div>
      </Card>
      </Col>
    </Row>
  </div>
</template>
<script>
import util from '@/libs/util'
import picUpload from '@/components/pic-upload'
import report from '@/components/report'
export default {
  name: 'index',
  components: {
    picUpload, report
  },
  data () {
    return {
      current: 0
    }
  },
  nextStep () {
    this.current++;
  },
  prevStep () {
    this.current--;
  }
}
</script>
