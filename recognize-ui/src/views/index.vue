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
          <Step title="上传匹对图片" content="上传一张自拍照和一张身份证正面照片，匹对两张图片的人脸是否为同一个人"></Step>
          <Step title="配对结果" content="图片相似程度，包括清晰度等参数"></Step>
        </Steps>
        <doc-upload v-if="current == 0" @next="nextStep" @setDocData="setDocData" :convertId="convertId"></doc-upload>
        <converting v-else-if="current == 1" :docList="docList" @next="nextStep" :convertId="convertId"></converting>
        <div v-else>
          wrong current value
        </div>
      </Card>
      </Col>
    </Row>
  </div>
</template>
<script>
export default {
  name: 'index',
  data () {
    return {
      current: 0
    }
  }
}
</script>
