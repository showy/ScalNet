/*
 *
 *  * Copyright 2017 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package org.deeplearning4j.scalnet.models

import org.deeplearning4j.scalnet.layers.{Dense, OutputLayer}
import org.nd4j.linalg.lossfunctions.LossFunctions.LossFunction
import org.scalatest.{BeforeAndAfter, FunSpec}

/**
  * Created by maxpumperla on 19/07/17.
  */
class NeuralNetTest extends FunSpec with BeforeAndAfter {

  var model: NeuralNet = NeuralNet()
  val shape = 100


  before {
    model = NeuralNet()
  }

  describe("A NeuralNet network") {

    it("without layers should produce a MatchError when compiled") {
      assertThrows[scala.MatchError] {
        model.compile(null)
      }
    }
    it("without buildOutput called should not have an output layer") {
      model.add(Dense(shape, shape))
      assert(!model.getLayers.last.asInstanceOf[OutputLayer].output.isOutput)
    }

    it("with buildOutput called should have an output layer") {
      model.add(Dense(shape, shape))
      model.buildOutput(LossFunction.NEGATIVELOGLIKELIHOOD)
      assert(model.getLayers.last.asInstanceOf[OutputLayer].output.isOutput)
    }

  }
}